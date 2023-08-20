package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SessionRepository
import com.droidknights.app2023.core.model.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeSessionRepository(
    private val bookmarkedSessionIds: Set<String>,
    private val sessions: List<Session>
) : SessionRepository {

    override suspend fun getSessions(): List<Session> {
        return sessions
    }

    override suspend fun getSession(sessionId: String): Session {
        return sessions.first { it.id == sessionId }
    }

    override suspend fun getBookmarkedSessionIds(): Flow<Set<String>> {
        return flow { emit(bookmarkedSessionIds) }
    }

    override suspend fun bookmarkSession(sessionId: String, bookmark: Boolean) {
        return
    }
}
