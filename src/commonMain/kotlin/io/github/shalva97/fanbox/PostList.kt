package io.github.shalva97.fanbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PostList(
    @SerialName("items")
    override val items: List<PostDetail>,
    @SerialName("nextUrl")
    override val nextUrl: String?,
    val count: Int? = null
) : ItemList, List<PostDetail> by items
