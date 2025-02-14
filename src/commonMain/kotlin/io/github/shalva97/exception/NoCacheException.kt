package io.github.shalva97.exception

import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.coroutines.cancel

public class NoCacheException(response: HttpResponse) : ResponseException(response, "<no cache>") {
    init {
        response.call.cancel(super.message!!)
    }

    override val message: String = "No Cache $response ${response.headers.toMap()}"
}