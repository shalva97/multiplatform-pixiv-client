package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserPreview(
    @SerialName("user")
    val user: UserInfo,
    @SerialName("illusts")
    val illusts: List<IllustInfo>,
    @SerialName("novels")
    val novels: List<NovelInfo>,
    @SerialName("is_muted")
    val isMuted: Boolean,
)