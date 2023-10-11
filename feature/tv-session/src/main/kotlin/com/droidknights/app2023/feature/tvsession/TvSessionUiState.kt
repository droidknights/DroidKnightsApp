package com.droidknights.app2023.feature.tvsession

import com.droidknights.app2023.core.model.Session
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

sealed interface TvSessionUiState {
    object Loading : TvSessionUiState
    data class Sessions(
        val sessions: PersistentList<Session> = persistentListOf(),
    ) : TvSessionUiState
}
