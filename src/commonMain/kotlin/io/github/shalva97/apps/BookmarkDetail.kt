package io.github.shalva97.apps

import io.github.shalva97.PublicityType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BookmarkDetail(
    @SerialName("is_bookmarked")
    val isBookmarked: Boolean,
    @SerialName("tags")
    val tags: List<TagInfo>,
    @SerialName("restrict")
    val restrict: PublicityType,
)