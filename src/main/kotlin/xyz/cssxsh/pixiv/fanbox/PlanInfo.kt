package xyz.cssxsh.pixiv.fanbox

import kotlinx.serialization.*

@Serializable
data class PlanInfo(
    @SerialName("coverImageUrl")
    val coverImageUrl: String?,
    @SerialName("creatorId")
    val creatorId: String,
    @SerialName("description")
    val description: String,
    @SerialName("fee")
    val fee: Int,
    @SerialName("hasAdultContent")
    val hasAdultContent: Boolean,
    @SerialName("id")
    val id: String,
    @SerialName("paymentMethod")
    val paymentMethod: String?,
    @SerialName("title")
    val title: String,
    @SerialName("user")
    val user: CreatorInfo
)