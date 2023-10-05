package io.github.shalva97.web

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserProfileIllusts(
    @SerialName("works")
    @Serializable(WebApiMap.Illust::class)
    val works: Map<Long, WebIllust>
)