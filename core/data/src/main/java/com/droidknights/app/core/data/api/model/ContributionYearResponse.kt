package com.droidknights.app.core.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributionYearResponse(
    @SerialName("id") val id: Long,
    @SerialName("years") val years: List<Int>,
)
