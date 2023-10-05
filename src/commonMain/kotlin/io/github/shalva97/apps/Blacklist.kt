package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Blacklist(
    @SerialName("users")
    val users: List<UserInfo>,
)