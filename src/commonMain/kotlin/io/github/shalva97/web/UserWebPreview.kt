package io.github.shalva97.web

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserWebPreview(
    @SerialName("acceptRequest")
    val acceptRequest: Boolean,
    @SerialName("followed")
    val followed: Boolean,
    @SerialName("following")
    val following: Boolean,
    @SerialName("illusts")
    val illusts: List<WebIllust>,
    @SerialName("isBlocking")
    val isBlocking: Boolean,
    @SerialName("isMypixiv")
    val isMypixiv: Boolean,
    @SerialName("novels")
    val novels: List<WebNovel>,
    @SerialName("profileImageUrl")
    val profileImageUrl: String,
    @SerialName("userComment")
    val userComment: String,
    @SerialName("userId")
    val uid: Long,
    @SerialName("userName")
    val name: String
)