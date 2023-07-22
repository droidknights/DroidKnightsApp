package com.droidknights.app2023.core.data.api.model

import com.droidknights.app2023.core.model.Speaker
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
internal data class SessionResponse(
    val title: String,
    val content: List<String>,
    val speakers: List<Speaker>,
    val level: LevelResponse,
    val tags: List<String>,
    val room: RoomResponse?,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
)
