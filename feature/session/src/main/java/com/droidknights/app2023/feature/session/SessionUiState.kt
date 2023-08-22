package com.droidknights.app2023.feature.session

import com.droidknights.app2023.core.model.Session
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class SessionUiState(
    val sessions: PersistentList<Session> = persistentListOf(),
)
