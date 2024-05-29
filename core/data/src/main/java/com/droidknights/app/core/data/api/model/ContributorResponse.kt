package com.droidknights.app.core.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ContributorResponse(
    val id: Long,
    @SerialName("login") val name: String,
    @SerialName("avatar_url") val imageUrl: String,
    @SerialName("html_url") val githubUrl: String,
)
