package io.github.shalva97.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WeiboQrcodeStatus(
    @SerialName("status")
    val code: Int,
    @SerialName("url")
    val url: String? = null
)