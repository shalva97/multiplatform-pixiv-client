package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TrendIllust(
    @SerialName("translated_name")
    val translatedName: String? = null,
    @SerialName("tag")
    val tag: String,
    @SerialName("illust")
    val illust: IllustInfo,
)