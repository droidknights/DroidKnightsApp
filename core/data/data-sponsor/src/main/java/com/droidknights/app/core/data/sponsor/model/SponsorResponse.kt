package com.droidknights.app.core.data.sponsor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SponsorResponse(
    @SerialName("name") val name: String,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("homepage") val homepage: String,
    @SerialName("grade") val grade: Grade,
) {

    enum class Grade {
        @SerialName("platinum")
        PLATINUM,

        @SerialName("gold")
        GOLD,

        @SerialName("silver")
        SILVER,
    }
}
