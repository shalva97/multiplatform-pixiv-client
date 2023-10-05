package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BookmarkDetailSingle(
    @SerialName("bookmark_detail")
    val detail: BookmarkDetail,
)