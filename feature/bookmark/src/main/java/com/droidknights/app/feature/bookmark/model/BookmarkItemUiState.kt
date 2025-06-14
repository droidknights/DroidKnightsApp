package com.droidknights.app.feature.bookmark.model

import com.droidknights.app.core.model.session.Session
import kotlinx.datetime.toJavaLocalDateTime
import java.time.LocalTime
import javax.annotation.concurrent.Immutable

@Immutable
data class BookmarkItemUiState(
    val index: Int,
    val session: Session,
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
