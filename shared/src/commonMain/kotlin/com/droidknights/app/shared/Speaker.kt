package com.droidknights.app.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Speaker(
    @SerialName("name")
    val name: String,
    @SerialName("introduction")
    val introduction: String,
    @SerialName("imageUrl")
    val imageUrl: String,
)
