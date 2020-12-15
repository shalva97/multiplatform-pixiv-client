package xyz.cssxsh.pixiv.client.exception

import io.ktor.client.features.*
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import xyz.cssxsh.pixiv.data.ApiError

class ApiException(response: HttpResponse, val apiError: ApiError) : ResponseException(response) {
    constructor(response: HttpResponse, json: String) : this(
        response,
        Json.decodeFromString(ApiError.serializer(), json)
    )

    override val message: String = apiError.error.message

    override fun toString(): String = response.run {
        "ApiException(url: ${call.request.url}, invalid: ${status}, header: ${request.headers.toMap()}, error: ${apiError})"
    }
}