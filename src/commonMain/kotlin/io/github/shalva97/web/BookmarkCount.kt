package io.github.shalva97.web

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BookmarkCount(
    @SerialName("illust")
    val illust: Int,
    @SerialName("novel")
    val novel: Int,
)