package com.droidknights.app.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributorGroup(
    @SerialName("name")
    val year: Int,
    @SerialName("contributors")
    val contributors: List<Contributor>,
)
