package com.droidknights.app.core.data.session.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SpeakerResponse(
    @SerialName("name") val name: String,
    @SerialName("introduction") val introduction: String,
    @SerialName("imageUrl") val imageUrl: String,
)
