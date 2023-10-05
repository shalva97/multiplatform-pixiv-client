package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class NovelData(
    @SerialName("novels")
    val novels: List<NovelInfo>,
    @SerialName("next_url")
    val nextUrl: String?,
    @SerialName("search_span_limit")
    val limit: Long? = null,
)