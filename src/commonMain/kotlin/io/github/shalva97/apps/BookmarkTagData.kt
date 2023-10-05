package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BookmarkTagData(
    @SerialName("bookmark_tags")
    val tags: List<BookmarkTag>,
    @SerialName("next_url")
    val nextUrl: String?,
) {
    @Serializable
    public data class BookmarkTag(
        @SerialName("name")
        val name: String,
        @SerialName("count")
        val count: Long,
    )
}