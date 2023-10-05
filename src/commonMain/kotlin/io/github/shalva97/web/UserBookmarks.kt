package io.github.shalva97.web

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserBookmarks<T : WebWorkInfo>(
    @SerialName("total")
    val total: Int,
    @SerialName("works")
    val works: List<T>
)