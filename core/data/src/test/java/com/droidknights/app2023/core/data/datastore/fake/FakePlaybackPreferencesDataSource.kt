package com.droidknights.app2023.core.data.datastore.fake

import com.droidknights.app2023.core.datastore.datasource.PlaybackPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePlaybackPreferencesDataSource : PlaybackPreferencesDataSource {
    private val _currentPlayingSessionId = MutableStateFlow<String?>(null)
    override val currentPlayingSessionId: Flow<String?> = _currentPlayingSessionId

    override suspend fun updateCurrentPlayingSession(sessionId: String) {
        _currentPlayingSessionId.value = sessionId
    }
}
