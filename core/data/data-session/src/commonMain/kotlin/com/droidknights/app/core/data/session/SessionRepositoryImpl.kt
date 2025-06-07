package com.droidknights.app.core.data.session

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.data.session.mapper.toData
import com.droidknights.app.core.data.session.model.SessionResponse
import com.droidknights.app.core.datastore.session.api.SessionPreferencesDataSource
import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

internal class SessionRepositoryImpl(
    private val sessionDataSource: SessionPreferencesDataSource,
    private val sessionApi: SessionApi,
) : SessionRepository {

    private val bookmarkIds: Flow<Set<String>> = sessionDataSource.bookmarkedSession

    override suspend fun getSessions(): List<Session> {
        val sessionResponses: List<SessionResponse> = sessionApi.getSessions()
        return sessionResponses.map { it.toData() }
    }

    override suspend fun getSession(sessionId: String): Session {
        return getSessions().first { it.id == sessionId } // TODO 없을 때 에러 처리
    }

    override fun getBookmarkedSessionIds(): Flow<Set<String>> = bookmarkIds

    override suspend fun bookmarkSession(sessionId: String, bookmark: Boolean) {
        val currentBookmarkedSessionIds = bookmarkIds.first()
        sessionDataSource.updateBookmarkedSession(
            if (bookmark) {
                currentBookmarkedSessionIds + sessionId
            } else {
                currentBookmarkedSessionIds - sessionId
            },
        )
    }

    override suspend fun deleteBookmarkedSessions(sessionIds: Set<String>) {
        val currentBookmarkedSessionIds = bookmarkIds.first()
        sessionDataSource.updateBookmarkedSession(
            currentBookmarkedSessionIds - sessionIds,
        )
    }
}
