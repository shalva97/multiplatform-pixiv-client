package io.github.shalva97.apps

import io.github.shalva97.CategoryType
import io.github.shalva97.FilterType
import io.github.shalva97.UseHttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*

public suspend fun UseHttpClient.spotlightArticles(
    category: CategoryType? = null,
    filter: FilterType? = null,
    offset: Long? = null,
    url: String = SPOTLIGHT_ARTICLES,
): SpotlightArticleData = useHttpClient { client ->
    client.get(url) {
        parameter("category", category)
        parameter("offset", offset)
        parameter("filter", filter)
    }.body()
}