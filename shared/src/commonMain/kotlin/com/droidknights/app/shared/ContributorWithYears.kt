package com.droidknights.app.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributorWithYears(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val years: List<Int>,
)
