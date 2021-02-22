@file:Suppress("unused")

package xyz.cssxsh.pixiv.tool

import io.ktor.client.engine.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import xyz.cssxsh.pixiv.*
import xyz.cssxsh.pixiv.api.apps.*
import xyz.cssxsh.pixiv.client.PixivClient
import xyz.cssxsh.pixiv.client.exception.*
import xyz.cssxsh.pixiv.data.apps.*
import java.time.OffsetDateTime
import kotlin.time.ExperimentalTime

fun PixivClient.task(
    name: String,
    block: suspend PixivClient.() -> Unit,
) = launch(CoroutineName("${this::class.qualifiedName}#Task: $name")) {
    block()
}

@ExperimentalTime
inline fun <reified T> PixivClient.timerTask(
    name: String,
    delay: Long,
    origin: T,
    crossinline block: suspend PixivClient.(prev: T) -> T,
) = launch(CoroutineName("${this::class.qualifiedName}#TimerTask: $name")) {
    var prev: T = origin
    while (isActive) {
        delay(delay)
        prev = block(prev)
    }
}

/**
 * 只会检查前30个新作品
 */
fun PixivClient.addUserListener(
    uid: Long,
    type: WorkContentType? = null,
    start: OffsetDateTime = OffsetDateTime.now(),
    delay: Long,
    block: suspend PixivClient.(IllustInfo) -> Unit,
) = timerTask(name = "UserListener($uid)", delay = delay, origin = start) {
    userIllusts(uid = uid, type = type).illusts.filter {
        it.createAt > start
    }.onEach {
        launch { block(it) }
    }.lastOrNull()?.createAt ?: start
}

/**
 * 只会检查前30个新作品
 */
suspend fun PixivClient.addIllustNewListener(
    type: WorkContentType = WorkContentType.ILLUST,
    restrict: PublicityType = PublicityType.PUBLIC,
    start: OffsetDateTime = OffsetDateTime.now(),
    delay: Long,
    block: suspend PixivClient.(IllustInfo) -> Unit,
) = timerTask(name = "IllustNewListener(${getAuthInfo().user.uid})", delay = delay, origin = start) {
    illustNew(type = type, restrict = restrict).illusts.filter {
        it.createAt > start
    }.onEach {
        launch { block(it) }
    }.lastOrNull()?.createAt ?: start
}

/**
 * 只会检查前30个新作品
 */
suspend fun PixivClient.addIllustMyPixivListener(
    type: WorkContentType = WorkContentType.ILLUST,
    restrict: PublicityType = PublicityType.PUBLIC,
    start: OffsetDateTime = OffsetDateTime.now(),
    delay: Long,
    block: suspend PixivClient.(IllustInfo) -> Unit,
) = timerTask(name = "IllustMyPixivListener(${getAuthInfo().user.uid})", delay = delay, origin = start) {
    illustMyPixiv(type = type, restrict = restrict).illusts.filter {
        it.createAt > start
    }.onEach {
        launch { block(it) }
    }.lastOrNull()?.createAt ?: start
}

/**
 * 只会检查前30个新作品
 */
suspend fun PixivClient.addIllustFollowListener(
    type: WorkContentType = WorkContentType.ILLUST,
    restrict: PublicityType = PublicityType.PUBLIC,
    start: OffsetDateTime = OffsetDateTime.now(),
    delay: Long,
    block: suspend PixivClient.(IllustInfo) -> Unit,
) = timerTask(name = "IllustFollowListener(${getAuthInfo().user.uid})", delay = delay, origin = start) {
    illustFollow(type = type, restrict = restrict).illusts.filter {
        it.createAt > start
    }.onEach {
        launch { block(it) }
    }.lastOrNull()?.createAt ?: start
}

internal fun String.toProxyConfig(): ProxyConfig = Url(this).let { url ->
    when (url.protocol.name) {
        "http" -> ProxyBuilder.http(url)
        "socks", "socks4", "socks5" -> ProxyBuilder.socks(url.host, url.port)
        else -> throw ProxyException(this)
    }
}
