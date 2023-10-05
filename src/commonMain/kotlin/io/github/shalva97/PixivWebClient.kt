package io.github.shalva97

import io.ktor.client.plugins.cookies.*

public interface PixivWebClient : UseHttpClient, UseConfig {
    public val storage: CookiesStorage
}