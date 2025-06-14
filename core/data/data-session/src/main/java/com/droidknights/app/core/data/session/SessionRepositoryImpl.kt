package com.droidknights.app.core.data.session

import com.droidknights.app.config.api.DroidknightsBuildConfig
import com.droidknights.app.core.data.session.api.SessionApi
import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.data.session.mapper.toData
import com.droidknights.app.core.datastore.session.api.SessionPreferencesDataSource
import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val sessionApi: SessionApi,
    private val sessionDataSource: SessionPreferencesDataSource,
    private val droidknightsBuildConfig: DroidknightsBuildConfig,
) : SessionRepository {

    private var cachedSessions: List<Session> = emptyList()

    private val bookmarkIds: Flow<Set<String>> = sessionDataSource.bookmarkedSession

    override suspend fun getSessions(): List<Session> {
        return sessionApi.getSessions(url = droidknightsBuildConfig.sessionsDataUrl())
            .map { it.toData() }
            .also { cachedSessions = it }
    }

    override suspend fun getSession(sessionId: String): Session {
        val cachedSession = cachedSessions.find { it.id == sessionId }
        if (cachedSession != null) {
            return cachedSession
        }

        return getSessions().find { it.id == sessionId }
            ?: error("Session not found with id: $sessionId")
    }

    override fun getBookmarkedSessionIds(): Flow<Set<String>> {
        return bookmarkIds.filterNotNull()
    }

    override suspend fun bookmarkSession(sessionId: String, bookmark: Boolean) {
        val currentBookmarkedSessionIds = bookmarkIds.first()
        sessionDataSource.updateBookmarkedSession(
            if (bookmark) {
                currentBookmarkedSessionIds + sessionId
            } else {
                currentBookmarkedSessionIds - sessionId
            }
        )
    }

    override suspend fun deleteBookmarkedSessions(sessionIds: Set<String>) {
        val currentBookmarkedSessionIds = bookmarkIds.first()
        sessionDataSource.updateBookmarkedSession(
            currentBookmarkedSessionIds - sessionIds
        )
    }
}
