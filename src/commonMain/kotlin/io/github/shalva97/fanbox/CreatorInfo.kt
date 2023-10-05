package io.github.shalva97.fanbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CreatorInfo(
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("iconUrl")
    val iconUrl: String?,
    @SerialName("name")
    val name: String,
    @SerialName("role")
    val role: String? = null,
    @SerialName("userId")
    val userId: Long
)