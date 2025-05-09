package com.droidknights.app.shared

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("speakers")
    val speakers: List<Speaker>,
    @SerialName("tags")
    val tags: List<Tag>,
    @SerialName("room")
    val room: Room,
    @SerialName("startTime")
    val startTime: LocalDateTime,
    @SerialName("endTime")
    val endTime: LocalDateTime,
    @SerialName("isBookmarked")
    val isBookmarked: Boolean,
)
