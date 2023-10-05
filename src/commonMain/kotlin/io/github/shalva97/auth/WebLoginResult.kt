package io.github.shalva97.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WebLoginResult(
    @SerialName("body")
    val body: Body,
    @SerialName("error")
    val error: Boolean,
    @SerialName("message")
    val message: String
) {
    @Serializable
    public data class Body(
        @SerialName("validation_errors")
        val validationErrors: Map<String, String> = emptyMap()
    )
}