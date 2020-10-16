package xyz.cssxsh.pixiv.api.public

import io.ktor.client.request.*
import io.ktor.http.*
import xyz.cssxsh.pixiv.client.PixivClient
import xyz.cssxsh.pixiv.data.public.RankingData
import xyz.cssxsh.pixiv.useHttpClient

suspend fun PixivClient.ranking(
    type: PublicApi.RankingType,
    mode: String,
    page: Int = 1,
    perPageNum: Int = 30,
    date: String? = null,
    includeStats: Boolean = true,
    includeSanityLevel: Boolean = true,
    imageSizes: List<String> = listOf(),
    profileImageSizes: List<String> = listOf(),
    url: String = PublicApi.RANKING(type)
): RankingData = useHttpClient { client ->
    client.get(url) {
        header(HttpHeaders.Referrer, PublicApi.REFERER)

        parameter("mode", mode)
        parameter("page", page)
        parameter("per_page", perPageNum)
        parameter("date", date)
        parameter("include_stats", includeStats)
        parameter("include_sanity_level", includeSanityLevel)
        parameter("imageSizes", imageSizes.joinToString(","))
        parameter("profileImageSizes", profileImageSizes.joinToString(","))
    }
}