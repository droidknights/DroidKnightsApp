package com.droidknights.app.widget

import androidx.compose.runtime.Immutable
import com.droidknights.app.core.model.Session

@Immutable
data class WidgetSessionCardUiState(
    val session: Session,
) {
    val speakerLabel: String
        get() = session.speakers.joinToString { it.name }
}