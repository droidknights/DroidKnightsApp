package com.droidknights.app2023.core.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Session(
    val title: String,
    val content: List<String>,
    val speakers: List<Speaker>,
    val level: Level,
    val tags: List<Tag>,
    val room: Room,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
)

