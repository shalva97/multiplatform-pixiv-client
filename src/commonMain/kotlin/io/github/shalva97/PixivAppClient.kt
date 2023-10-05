package io.github.shalva97

import io.github.shalva97.auth.AuthResult
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope

/**
 * PixivClient，Multiplatform interface
 */
public interface PixivAppClient : UseHttpClient, UseConfig, CoroutineScope {

    public suspend fun login(block: suspend (Url) -> String): AuthResult

    public suspend fun refresh(): AuthResult

    public suspend fun info(): AuthResult

    public val refreshToken: String

    public val deviceToken: String

    public val ageLimit: AgeLimit
}