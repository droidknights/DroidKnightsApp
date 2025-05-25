package com.droidknights.app.widget

import androidx.compose.runtime.Immutable
import com.droidknights.app.core.model.session.Session

@Immutable
data class WidgetSessionCardUiState(
    val session: Session,
) {
    val speakerLabel: String by lazy { session.speakers.joinToString { it.name } }
}
