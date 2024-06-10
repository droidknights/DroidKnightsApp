package com.droidknights.app.core.data.api.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SessionResponse(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("speakers") val speakers: List<SpeakerResponse>,
    @SerialName("tags") val tags: List<String>,
    @SerialName("room") val room: RoomResponse?,
    @SerialName("startTime") val startTime: LocalDateTime,
    @SerialName("endTime") val endTime: LocalDateTime,
)
