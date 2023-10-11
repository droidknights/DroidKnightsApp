package com.droidknights.app2023.feature.wearsession

import com.droidknights.app2023.core.model.Session
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

sealed interface WearSessionUiState {
    object Loading : WearSessionUiState
    data class Sessions(
        val sessions: PersistentList<Session> = persistentListOf(),
    ) : WearSessionUiState
}
