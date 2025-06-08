package com.droidknights.app.core.data.contributor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ContributionYearResponse(
    @SerialName("id") val id: Long,
    @SerialName("years") val years: List<Int>,
)
