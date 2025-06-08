package com.droidknights.app.feature.bookmark.model

import androidx.compose.runtime.Immutable
import com.droidknights.app.core.model.session.Session
import kotlinx.datetime.LocalTime

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
        get() = session.startTime.time
}
