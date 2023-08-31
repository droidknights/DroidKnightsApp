package com.droidknights.app2023.core.data.datastore.fake

import com.droidknights.app2023.core.data.datasource.SessionLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class FakeSessionPreferencesDataSource : SessionLocalDataSource {
    private val _bookmarkedSession = MutableStateFlow(emptySet<String>())
    override val bookmarkedSession: Flow<Set<String>> = _bookmarkedSession.filterNotNull()

    override suspend fun updateBookmarkedSession(bookmarkedSession: Set<String>) {
        _bookmarkedSession.value = bookmarkedSession.toSet()
    }
}
