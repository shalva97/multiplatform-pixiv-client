package io.github.shalva97.apps

import io.github.shalva97.FilterType
import io.github.shalva97.PixivAppClient
import io.ktor.client.call.*
import io.ktor.client.request.*

public suspend fun PixivAppClient.trendingTagsIllust(
    filter: FilterType? = null,
    url: String = TRENDING_TAGS_ILLUST,
): TrendIllustData = useHttpClient { client ->
    client.get(url) {
        parameter("filter", filter)
    }.body()
}