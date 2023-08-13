package com.droidknights.app2023.feature.session

import com.droidknights.app2023.core.model.Session

sealed interface SessionDetailUiState {

    object Loading : SessionDetailUiState

    data class Success(val session: Session, val bookmarked: Boolean = false) : SessionDetailUiState
}
