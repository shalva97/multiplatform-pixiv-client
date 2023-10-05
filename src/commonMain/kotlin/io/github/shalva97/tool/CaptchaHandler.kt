package io.github.shalva97.tool

/**
 * handle ReCaptcha
 */
public interface CaptchaHandler {
    public suspend fun handle(siteKey: String, referer: String): String
}