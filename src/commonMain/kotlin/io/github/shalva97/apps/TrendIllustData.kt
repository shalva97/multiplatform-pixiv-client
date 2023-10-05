package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TrendIllustData(
    @SerialName("trend_tags")
    val trends: List<TrendIllust>,
)