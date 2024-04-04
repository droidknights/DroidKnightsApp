package com.droidknights.app.feature.session

import com.droidknights.app.core.model.Session
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

sealed interface SessionUiState {

    data object Loading : SessionUiState

    data class Sessions(
        val sessions: PersistentList<Session> = persistentListOf(),
    ) : SessionUiState
}
