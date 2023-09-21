package com.droidknights.app2023.core.repo.session.api.model

import kotlinx.datetime.LocalDateTime

data class Session(
    val id: String,
    val title: String,
    val content: String,
    val speakers: List<Speaker>,
    val level: Level,
    val tags: List<Tag>,
    val room: Room,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val isBookmarked: Boolean
)
