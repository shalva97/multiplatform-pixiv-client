package io.github.shalva97.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WeiboQrcode(
    @SerialName("url")
    val url: String,
    @SerialName("vcode")
    val vcode: String
)