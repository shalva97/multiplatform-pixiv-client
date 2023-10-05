package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BookmarkRangeData(
    @SerialName("bookmark_ranges")
    val ranges: List<BookmarkRange>
) {
    @Serializable
    public data class BookmarkRange(
        @SerialName("bookmark_num_max")
        val max: String,
        @SerialName("bookmark_num_min")
        val min: String
    )
}