package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class EmojiDefinitionData(
    @SerialName("emoji_definitions")
    val emoticons: List<EmojiDefinition>
) {
    @Serializable
    public data class EmojiDefinition(
        @SerialName("id")
        val id: Int,
        @SerialName("image_url_medium")
        val imageUrlMedium: String,
        @SerialName("slug")
        val slug: String
    )
}