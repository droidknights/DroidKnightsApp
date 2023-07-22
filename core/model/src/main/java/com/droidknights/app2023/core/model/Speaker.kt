package com.droidknights.app2023.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Speaker(
    val name: String,
    val imageUrl: String,
)
