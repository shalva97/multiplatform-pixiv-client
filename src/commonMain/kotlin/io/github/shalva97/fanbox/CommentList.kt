package io.github.shalva97.fanbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CommentList(
    @SerialName("items")
    override val items: List<CommentInfo>,
    @SerialName("nextUrl")
    override val nextUrl: String?
) : ItemList, List<CommentInfo> by items {
    public companion object {
        public val Empty: CommentList = CommentList(items = emptyList(), nextUrl = null)
    }
}