package io.github.shalva97

import io.ktor.client.engine.*
import io.ktor.client.engine.curl.*

internal actual val httpClient: HttpClientEngineFactory<*> = Curl
