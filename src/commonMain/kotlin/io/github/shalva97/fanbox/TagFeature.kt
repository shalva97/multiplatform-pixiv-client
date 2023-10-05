package io.github.shalva97.fanbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TagFeature(
    @SerialName("count")
    val count: Int,
    @SerialName("coverImageUrl")
    val coverImageUrl: String?,
    @SerialName("tag")
    val tag: String
)