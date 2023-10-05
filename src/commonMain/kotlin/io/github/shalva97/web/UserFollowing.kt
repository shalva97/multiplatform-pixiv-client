package io.github.shalva97.web

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserFollowing(
    @SerialName("followUserTags")
    val tags: List<String>,
    @SerialName("total")
    val total: Long,
    @SerialName("users")
    val users: List<UserWebPreview>
)