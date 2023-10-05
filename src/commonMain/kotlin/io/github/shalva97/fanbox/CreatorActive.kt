package io.github.shalva97.fanbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CreatorActive(
    @SerialName("creatorId")
    val creatorId: String,
    @SerialName("isActive")
    val isActive: Boolean,
    @SerialName("user")
    val user: CreatorInfo
)