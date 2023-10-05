package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpotlightArticleData(
    @SerialName("spotlight_articles")
    val articles: List<SpotlightArticle>,
    @SerialName("next_url")
    val nextUrl: String?,
)