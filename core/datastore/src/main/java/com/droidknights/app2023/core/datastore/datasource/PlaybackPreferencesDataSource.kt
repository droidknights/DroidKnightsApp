package com.droidknights.app2023.core.datastore.datasource

import kotlinx.coroutines.flow.Flow

interface PlaybackPreferencesDataSource {
    val currentPlayingSessionId: Flow<String?>
    suspend fun updateCurrentPlayingSession(sessionId: String)
}
