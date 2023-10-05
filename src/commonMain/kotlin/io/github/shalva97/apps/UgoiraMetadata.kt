package io.github.shalva97.apps

import io.github.shalva97.FileUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UgoiraMetadata(
    @SerialName("frames")
    val frames: List<UgoiraFrame>,
    @SerialName("zip_urls")
    val zipUrls: FileUrls,
)
