package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class IllustData(
    @SerialName("illusts")
    val illusts: List<IllustInfo>,
    @SerialName("next_url")
    val nextUrl: String? = null,
    @SerialName("search_span_limit")
    val limit: Long? = null,
)