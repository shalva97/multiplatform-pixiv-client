package xyz.cssxsh.pixiv

import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.cookies.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.logging.*
import org.openqa.selenium.remote.*
import xyz.cssxsh.pixiv.auth.*
import xyz.cssxsh.pixiv.tool.*

public const val WEIBO_QRCODE_GENERATE: String = "https://api.weibo.com/oauth2/qrcode_authorize/generate"

public const val WEIBO_QRCODE_QUERY: String = "https://api.weibo.com/oauth2/qrcode_authorize/query"

public const val WEIBO_QRCODE_TIMEOUT: Long = 10 * 60 * 1000L

public const val WEIBO_QRCODE_INTERVAL: Long = 3 * 1000L

private fun HttpMessage.location() = headers[HttpHeaders.Location]?.let(::Url)

@OptIn(ExperimentalSerializationApi::class)
private suspend fun HttpResponse.account(): HtmlAccount {
    val html = receive<String>()
    val data = html.substringAfter("value='").substringBefore("'")
    return PixivJson.decodeFromString(data)
}

/**
 * @param link for [POST_REDIRECT_URL]
 */
private suspend fun PixivAuthClient.redirect(link: Url): String {
    val redirect: HttpResponse = useHttpClient { it.get(link) }
    val location = redirect.request.url

    check(location.toString().startsWith(POST_REDIRECT_URL)) { location }

    val account = redirect.account()

    checkNotNull(account.uid) { "未登录" }

    val current = Url(account.returnTo)

    /**
     * current for [START_URL]
     */
    val response: HttpMessage = useHttpClient {
        it.post(current) {
            expectSuccess = false

            header(HttpHeaders.Origin, ORIGIN_URL)
            header(HttpHeaders.Referrer, LOGIN_URL)
        }
    }

    val authorize = requireNotNull(response.location()) { "跳转到 $OAUTH_AUTHORIZE_URL 失败" }

    /**
     * authorize for [OAUTH_AUTHORIZE_URL]
     */
    val code: HttpMessage = useHttpClient {
        it.get(authorize) {
            expectSuccess = false

            header(HttpHeaders.Origin, ORIGIN_URL)
            header(HttpHeaders.Referrer, LOGIN_URL)
        }
    }

    val scheme = requireNotNull(code.location()) { "跳转到 pixiv://... 失败" }

    /**
     * Code by Url pixiv://...
     */
    return scheme.parameters["code"] ?: throw NoSuchElementException("code, $scheme")
}

/**
 * 登录，通过新浪微博关联Pixiv
 * @param show handle qrcode image url
 */
@OptIn(ExperimentalSerializationApi::class)
public suspend fun PixivAuthClient.sina(show: suspend (qrcode: Url) -> Unit): AuthResult = login { redirect ->
    /**
     * for [LOGIN_URL]
     */
    val html: String = useHttpClient { it.get(redirect) }

    val all = html.let("""gigya-auth[^"]+""".toRegex()::findAll).map { it.value.replace("&amp;", "&") }
    val sina = Url(ORIGIN_URL).copy(encodedPath = all.first { "sina" in it })

    // Jump to Sina Weibo
    val temp: HttpResponse = useHttpClient { it.head(sina) }
    // 为了直接继承参数 ↓
    val generate = Url(WEIBO_QRCODE_GENERATE).copy(parameters = temp.request.url.parameters)
    val qrcode: WeiboQrcode = PixivJson.decodeFromString(useHttpClient { it.get(generate) })

    supervisorScope {
        show(Url(qrcode.url))
    }

    val jump = withTimeout(WEIBO_QRCODE_TIMEOUT) {
        lateinit var auto: String
        while (isActive) {
            delay(WEIBO_QRCODE_INTERVAL)
            val status: WeiboQrcodeStatus = PixivJson.decodeFromString(useHttpClient {
                it.get(WEIBO_QRCODE_QUERY) {
                    parameter("vcode", qrcode.vcode)
                }
            })
            auto = status.url ?: continue
            break
        }
        auto
    }

    // replace protocol for ssl with g-client-proxy.pixiv.net
    val gigya: String = useHttpClient {
        it.get(Url(jump).copy(protocol = URLProtocol.HTTPS, host = "d1ctzrip8l97jt.cloudfront.net"))
    }

    val sign = gigya.substringAfter("redirect('").substringBefore("');")
    // transform unicode
    val unicode = """\\u(\d{4})""".toRegex()
    val link = Url(sign.replace(unicode) { it.destructured.component1().toInt(16).toChar().toString() })

    /**
     * GiGya auto to [POST_REDIRECT_URL]
     */
    redirect(link = link)
}

/**
 * 登录，通过 Web Cookies
 * @param load get pixiv web cookie
 */
public suspend fun PixivAuthClient.cookie(load: () -> List<Cookie>): AuthResult = login { redirect ->
    for (cookie in load()) {
        storage.addCookie(ORIGIN_URL, cookie)
    }
    requireNotNull(storage.get(Url(ORIGIN_URL))["PHPSESSID"]) { "PHPSESSID is null" }
    val login: HttpResponse = useHttpClient { it.get(redirect) }
    val account = login.account()

    checkNotNull(account.uid) { "未登录" }

    /**
     * for [POST_SELECTED_URL]
     */
    val response: HttpMessage = useHttpClient {
        it.post(POST_SELECTED_URL) {
            expectSuccess = false

            header(HttpHeaders.Origin, ORIGIN_URL)
            header(HttpHeaders.Referrer, LOGIN_URL)

            body = FormDataContent(Parameters.build {
                append("return_to", account.current)
                append("tt", account.token)
            })
        }
    }

    val link = requireNotNull(response.location()) { "跳转到 $POST_REDIRECT_URL 失败" }

    redirect(link = link)
}

/**
 * 登录，通过 账户密码
 * @param id Pixiv ID 或者 Email
 * @param pwd 密码
 * @param handler 人机验证处理
 */
public suspend fun PixivAuthClient.password(id: String, pwd: String, handler: CaptchaHandler): AuthResult = login { r ->
    /**
     * for [LOGIN_URL]
     */
    val login: HttpResponse = useHttpClient { it.get(r) }
    val account = login.account()

    while (isActive) {
        /**
         * 实际上 SiteKey 是固定值 6LfF1dcZAAAAAOHQX8v16MX5SktDwmQINVD_6mBF
         */
        val gRecaptchaResponse =
            handler.handle(siteKey = account.scoreSiteKey, referer = login.request.url.toString())

        val attempt: JsonObject = useHttpClient {
            it.post(LOGIN_API_URL) {

                header(HttpHeaders.Origin, ORIGIN_URL)
                header(HttpHeaders.Referrer, "https://accounts.pixiv.net/")

                parameter("lang", "zh")

                body = FormDataContent(Parameters.build {
                    append("password", pwd)
                    append("pixiv_id", id)
                    append("captcha", "")
                    append("g-recaptcha-response", gRecaptchaResponse)
                    append("post_key", account.postKey)
                    append("source", account.source)
                    append("ref", account.ref)
                    append("return_to", account.returnTo)
                    append("recaptcha_enterprise_score_token", gRecaptchaResponse)
                    append("tt", account.token)
                })
            }
        }

        val result = PixivJson.decodeFromJsonElement<WebLoginResult>(attempt)

        if ("captcha" in result.body.validationErrors) continue

        check(result.body.validationErrors.isNotEmpty()) { result.body }

        break
    }

    /**
     * for [POST_SELECTED_URL]
     */
    val message: HttpMessage = useHttpClient {
        it.post(POST_SELECTED_URL) {
            expectSuccess = false

            header(HttpHeaders.Origin, ORIGIN_URL)
            header(HttpHeaders.Referrer, LOGIN_URL)

            body = FormDataContent(Parameters.build {
                append("return_to", account.current)
                append("tt", account.token)
            })
        }
    }

    val link = requireNotNull(message.location()) { "跳转到 $POST_REDIRECT_URL 失败" }

    redirect(link = link)
}

/**
 * 登录，通过 selenium 调用浏览器
 * @param driver 浏览器驱动器
 */
public suspend fun PixivAuthClient.selenium(driver: RemoteWebDriver, timeout: Long): AuthResult = login { redirect ->
    try {
        driver.get(redirect.toString())
    } catch (_: WebDriverException) {
        //
    }
    withTimeout(timeout) {
        while (driver.currentUrl.orEmpty().startsWith(POST_REDIRECT_URL).not()) {
            delay(10_000)
        }
    }
    // XXX: 通过错误日志获取 跳转URL
    val log = driver.manage().logs().get(LogType.BROWSER)
        .last { log -> "pixiv://account/login" in log.message.orEmpty() }
    val url = Url(log.message.substringAfter("'").substringBefore("'"))

    url.parameters["code"] ?: throw NoSuchElementException("code, ${log.message}")
}