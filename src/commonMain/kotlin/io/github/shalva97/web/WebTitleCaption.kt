package io.github.shalva97.web

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WebTitleCaption(
    @SerialName("workCaption")
    val caption: String?,
    @SerialName("workTitle")
    val title: String?
)