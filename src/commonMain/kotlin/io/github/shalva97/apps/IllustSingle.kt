package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class IllustSingle(
    @SerialName("illust")
    val illust: IllustInfo,
)