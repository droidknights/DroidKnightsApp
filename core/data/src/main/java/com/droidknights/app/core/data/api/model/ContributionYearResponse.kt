package com.droidknights.app.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ContributionYearResponse(
    val id: Long,
    val years: List<Int>,
)
