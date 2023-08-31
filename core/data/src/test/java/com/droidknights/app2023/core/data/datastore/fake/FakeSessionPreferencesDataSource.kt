package com.droidknights.app2023.core.data.datastore.fake

import com.droidknights.app2023.core.data.datasource.SessionLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSessionPreferencesDataSource : SessionLocalDataSource {
    private val _bookmarkedSession = mutableSetOf<String>()
    override val bookmarkedSession: Flow<Set<String>> = flow {
        emit(_bookmarkedSession)
    }

    override suspend fun updateBookmarkedSession(newSession: Set<String>) {
        _bookmarkedSession.clear()
        _bookmarkedSession.addAll(newSession)
    }
}
