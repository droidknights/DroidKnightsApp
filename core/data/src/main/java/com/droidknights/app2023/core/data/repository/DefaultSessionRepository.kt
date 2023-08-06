package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.data.api.GithubRawApi
import com.droidknights.app2023.core.data.mapper.toData
import com.droidknights.app2023.core.model.Session
import javax.inject.Inject

internal class DefaultSessionRepository @Inject constructor(
    private val githubRawApi: GithubRawApi,
) : SessionRepository {
    private var cachedSessions: List<Session> = emptyList()

    /**
     * TODO : 북마크 아이디가 앱이 종료된 이후에도 유지되도록 한다
     */
    private var bookmarkIds: Set<String> = emptySet()

    override suspend fun getSessions(): List<Session> {
        return githubRawApi.getSessions()
            .map { it.toData() }
            .also { cachedSessions = it }
    }

    override suspend fun getSession(sessionId: String): Session {
        val cachedSession = cachedSessions.find { it.id == sessionId }
        if (cachedSession != null) {
            return cachedSession
        }
        return getSessions().find { it.id == sessionId }
            ?: throw IllegalStateException("Session not found with id: $sessionId")
    }

    override suspend fun getBookmarkedSessionIds(): List<String> {
        return bookmarkIds.toList()
    }
}
