package com.droidknights.app.core.domain.session.usecase.fake

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.model.session.Session
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

    override fun getBookmarkedSessionIds(): Flow<Set<String>> {
        return flow { emit(bookmarkedSessionIds) }
    }

    override suspend fun bookmarkSession(sessionId: String, bookmark: Boolean) {
        return
    }

    override suspend fun deleteBookmarkedSessions(sessionIds: Set<String>) {
        TODO("Not yet implemented")
    }
}
