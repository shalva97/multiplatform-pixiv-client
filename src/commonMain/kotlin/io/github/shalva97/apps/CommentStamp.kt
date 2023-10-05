package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CommentStamp(
    @SerialName("stamp_id")
    val id: Long,
    @SerialName("stamp_url")
    val url: String,
)