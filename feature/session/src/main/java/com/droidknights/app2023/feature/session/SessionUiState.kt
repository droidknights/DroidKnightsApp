package com.droidknights.app2023.feature.session

import com.droidknights.app2023.core.repo.session.api.model.Session
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

sealed interface SessionUiState {
    object Loading : SessionUiState
    data class Sessions(
        val sessions: PersistentList<Session> = persistentListOf(),
    ) : SessionUiState
}
