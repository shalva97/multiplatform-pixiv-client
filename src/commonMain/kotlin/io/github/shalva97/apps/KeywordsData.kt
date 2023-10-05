package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class KeywordsData(
    @SerialName("search_auto_complete_keywords")
    val keywords: List<String>,
)