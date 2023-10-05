package io.github.shalva97.exception

import io.github.shalva97.web.WebApiResult

public class WebApiException(public val result: WebApiResult) : IllegalArgumentException() {
    override val message: String get() = result.message
}