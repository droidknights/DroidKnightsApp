package com.droidknights.app2023.core.data.mapper

import com.droidknights.app2023.core.data.api.model.SessionResponse
import com.droidknights.app2023.core.model.Session

internal fun SessionResponse.toData(): Session = Session(
    title = this.title,
    content = this.content,
    speakers = this.speakers,
    level = this.level,
    tags = this.tags,
    room = this.room,
    startTime = this.startTime,
    endTime = this.endTime
)
