package com.droidknights.app.shared

import kotlinx.datetime.LocalDateTime

data class Session(
    val id: String,
    val title: String,
    val content: String,
    val speakers: List<Speaker>,
    val tags: List<Tag>,
    val room: Room,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val isBookmarked: Boolean
)
