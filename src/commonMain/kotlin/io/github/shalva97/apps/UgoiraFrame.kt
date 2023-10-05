package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UgoiraFrame(
    @SerialName("delay")
    val delay: Long,
    @SerialName("file")
    val file: String,
)
