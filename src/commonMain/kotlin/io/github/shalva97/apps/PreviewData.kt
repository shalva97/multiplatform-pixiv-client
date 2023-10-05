package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PreviewData(
    @SerialName("user_previews")
    val previews: List<UserPreview>,
    @SerialName("next_url")
    val nextUrl: String?,
)