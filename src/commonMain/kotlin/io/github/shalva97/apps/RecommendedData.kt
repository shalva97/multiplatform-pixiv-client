package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RecommendedData(
    @SerialName("illusts")
    val illusts: List<IllustInfo>,
    @SerialName("ranking_illusts")
    val rankingIllusts: List<IllustInfo>,
    @SerialName("contest_exists")
    val contestExists: Boolean,
    @SerialName("privacy_policy")
    val privacyPolicy: Map<String, String>,
    @SerialName("next_url")
    val nextUrl: String?,
)