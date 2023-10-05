package io.github.shalva97.apps

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class StampData(
    @SerialName("stamps")
    val stamps: List<Stamp>
) {
    @Serializable
    public data class Stamp(
        @SerialName("stamp_id")
        val stampId: Int,
        @SerialName("stamp_url")
        val stampUrl: String
    )
}