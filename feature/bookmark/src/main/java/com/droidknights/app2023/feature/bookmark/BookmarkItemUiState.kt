package com.droidknights.app2023.feature.bookmark

import com.droidknights.app2023.core.model.Session
import kotlinx.datetime.toJavaLocalDateTime
import java.time.LocalTime

data class BookmarkItemUiState(
    val index: Int,
    val session: Session,
    val isEditMode: Boolean
) {
    val sequence: Int
        get() = index + 1

    val tagLabel: String
        get() = session.tags.joinToString { it.name }

    val speakerLabel: String
        get() = session.speakers.joinToString { it.name }

    val time: LocalTime
        get() = session.startTime.toJavaLocalDateTime().toLocalTime()
}
