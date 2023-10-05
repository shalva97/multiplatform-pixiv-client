package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UgoiraInfo(
    @SerialName("ugoira_metadata")
    val ugoira: UgoiraMetadata,
)