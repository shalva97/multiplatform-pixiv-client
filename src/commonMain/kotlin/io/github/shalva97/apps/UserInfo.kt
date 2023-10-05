package io.github.shalva97.apps

import io.github.shalva97.FileUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserInfo(
    @SerialName("account")
    val account: String,
    @SerialName("id")
    val id: Long,
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("is_followed")
    val isFollowed: Boolean? = null,
    @SerialName("is_access_blocking_user")
    val isAccessBlockingUser: Boolean? = null,
    @SerialName("name")
    val name: String,
    @SerialName("profile_image_urls")
    val profileImageUrls: FileUrls,
)