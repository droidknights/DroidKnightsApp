package com.droidknights.app.feature.session.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.droidknights.app.core.model.Session

@Stable
sealed interface SessionDetailUiState {

    @Immutable
    data object Loading : SessionDetailUiState

    @Immutable
    data class Success(val session: Session, val bookmarked: Boolean = false) : SessionDetailUiState
}
