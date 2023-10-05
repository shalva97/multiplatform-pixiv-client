package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TagInfo(
    @SerialName("name")
    val name: String,
    @SerialName("translated_name")
    val translatedName: String? = null,
    @SerialName("added_by_uploaded_user")
    val addedByUploadedUser: Boolean? = null,
    @SerialName("is_registered")
    val isRegistered: Boolean? = null,
)