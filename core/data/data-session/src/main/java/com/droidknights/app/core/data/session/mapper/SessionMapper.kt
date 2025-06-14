package com.droidknights.app.core.data.session.mapper

import com.droidknights.app.core.data.session.model.RoomResponse
import com.droidknights.app.core.data.session.model.SessionResponse
import com.droidknights.app.core.data.session.model.SpeakerResponse
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.core.model.session.Tag

internal fun SessionResponse.toData(): Session =
    Session(
        id = this.id,
        title = this.title,
        content = this.content,
        speakers = this.speakers.map { it.toData() },
        tags = this.tags.map { Tag(it) },
        room = this.room?.toData() ?: Room.ETC,
        startTime = this.startTime,
        endTime = this.endTime,
        isBookmarked = false,
    )

internal fun RoomResponse.toData(): Room =
    when (this) {
        RoomResponse.ETC -> Room.ETC
        RoomResponse.TRACK1 -> Room.TRACK1
        RoomResponse.TRACK2 -> Room.TRACK2
    }

internal fun SpeakerResponse.toData(): Speaker =
    Speaker(
        name = this.name,
        introduction = this.introduction,
        imageUrl = this.imageUrl,
    )
