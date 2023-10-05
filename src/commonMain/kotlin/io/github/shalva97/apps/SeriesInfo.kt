package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SeriesInfo(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("title")
    val title: String? = null,
)