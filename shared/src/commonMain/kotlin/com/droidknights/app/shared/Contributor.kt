package com.droidknights.app.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contributor(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("githubUrl")
    val githubUrl: String,
)
