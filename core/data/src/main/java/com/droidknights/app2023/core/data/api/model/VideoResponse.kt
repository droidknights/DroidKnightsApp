package com.droidknights.app2023.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
internal data class VideoResponse(
    val manifestUrl: String,
    val thumbnailUrl: String,
)