package com.droidknights.app.core.data.contributor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributionYearResponse(
    @SerialName("login") val login: String,
    @SerialName("id") val id: Long,
    @SerialName("years") val years: List<Int>,
)
