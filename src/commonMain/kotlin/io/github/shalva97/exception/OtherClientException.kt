package io.github.shalva97.exception

import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

public class OtherClientException(response: HttpResponse, content: String) : ResponseException(response, content) {

    public val json: JsonElement = Json.parseToJsonElement(content)

    override fun toString(): String = with(response) {
        "OtherException(url: ${call.request.url}, invalid: ${status}, headers: ${request.headers.toMap()}, json: ${json})"
    }
}