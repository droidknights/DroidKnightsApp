package com.droidknights.app2023.core.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ContributorResponse(
    @SerialName("login") val name: String,
    @SerialName("avatar_url") val imageUrl: String,
    @SerialName("html_url") val githubUrl: String,
)
