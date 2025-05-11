package com.droidknights.app.core.data.session.api

import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun getSessions(): List<Session>

    suspend fun getSession(sessionId: String): Session

    fun getBookmarkedSessionIds(): Flow<Set<String>>

    suspend fun bookmarkSession(sessionId: String, bookmark: Boolean)

    suspend fun deleteBookmarkedSessions(sessionIds: Set<String>)
}
