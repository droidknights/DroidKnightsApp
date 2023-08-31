package com.droidknights.app2023.core.data.datasource

import kotlinx.coroutines.flow.Flow

interface SessionLocalDataSource {
    val bookmarkedSession: Flow<Set<String>>
    suspend fun updateBookmarkedSession(bookmarkedSession: Set<String>)
}
