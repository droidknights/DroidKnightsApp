package com.droidknights.app2023.feature.session

import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session

data class SessionUiState(
    val sessions: List<Session> = emptyList(),
) {

    fun getSessionsBy(room: Room): List<Session> {
        return sessions.filter { it.room == room }
    }
}
