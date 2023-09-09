package com.droidknights.app2023.core.repo.session.api

import com.droidknights.app2023.core.repo.session.api.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun getSessions(): List<Session>

    suspend fun getSession(sessionId: String): Session

    suspend fun getBookmarkedSessionIds(): Flow<Set<String>>

    suspend fun bookmarkSession(sessionId: String, bookmark: Boolean)
}
