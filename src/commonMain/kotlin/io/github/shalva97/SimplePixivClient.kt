package io.github.shalva97

import io.ktor.utils.io.errors.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public open class SimplePixivClient(
    parentCoroutineContext: CoroutineContext = EmptyCoroutineContext,
    coroutineName: String = "SimplePixivClient",
    override val config: PixivConfig,
) : PixivAuthClient(), PixivWebClient {

    override val coroutineContext: CoroutineContext by lazy {
        parentCoroutineContext + CoroutineName(coroutineName) + SupervisorJob()
    }

    override val ignore: suspend (Throwable) -> Boolean = { it is IOException }
}