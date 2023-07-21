package com.droidknights.app2023.core.data.api.model

import com.droidknights.app2023.core.model.Level
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Speaker
import com.droidknights.app2023.core.model.Tag
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
internal data class SessionResponse(
    val title: String,
    val content: List<String>,
    val speakers: List<Speaker>,
    val level: Level,
    val tags: List<Tag> = emptyList(),
    val room: Room = Room.ETC,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
)
