package com.droidknights.app2023.core.data.mapper

import com.droidknights.app2023.core.data.api.model.LevelResponse
import com.droidknights.app2023.core.data.api.model.RoomResponse
import com.droidknights.app2023.core.data.api.model.SessionResponse
import com.droidknights.app2023.core.model.Level
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.model.Tag

internal fun SessionResponse.toData(): Session = Session(
    title = this.title,
    content = this.content,
    speakers = this.speakers,
    level = this.level.toData(),
    tags = this.tags.map { Tag(it) },
    room = this.room?.toData() ?: Room.ETC,
    startTime = this.startTime,
    endTime = this.endTime
)

internal fun LevelResponse.toData(): Level = when (this) {
    LevelResponse.ETC -> Level.ETC
    LevelResponse.BASIC -> Level.BASIC
    LevelResponse.INTERMEDIATE -> Level.INTERMEDIATE
    LevelResponse.ADVANCED -> Level.ADVANCED
}

internal fun RoomResponse.toData(): Room = when (this) {
    RoomResponse.ETC -> Room.ETC
    RoomResponse.TRACK1 -> Room.TRACK1
    RoomResponse.TRACK2 -> Room.TRACK2
    RoomResponse.TRACK3 -> Room.TRACK3
}
