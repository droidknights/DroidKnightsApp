package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.model.Session

interface SessionRepository {

    suspend fun getSessions(): List<Session>
    
    suspend fun getSession(sessionId: String): Session
    
    suspend fun getBookmarkedSessionIds(): List<String>
}
