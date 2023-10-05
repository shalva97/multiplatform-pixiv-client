package io.github.shalva97.web

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserRequestInfo(
    @SerialName("postWorks")
    val postWorks: PostWorks,
    @SerialName("showRequestSentTab")
    val showRequestSentTab: Boolean,
    @SerialName("showRequestTab")
    val showRequestTab: Boolean
) {
    @Serializable
    public data class PostWorks(
        @SerialName("artworks")
        @Serializable(WepApiSet::class)
        val artworks: Set<Long>,
        @SerialName("novels")
        @Serializable(WepApiSet::class)
        val novels: Set<Long>
    )
}