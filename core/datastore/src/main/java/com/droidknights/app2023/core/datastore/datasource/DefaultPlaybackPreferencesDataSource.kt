package com.droidknights.app2023.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultPlaybackPreferencesDataSource @Inject constructor(
    @Named("playback") private val dataStore: DataStore<Preferences>
) : PlaybackPreferencesDataSource {
    object PreferencesKey {
        val CURRENT_SESSION_ID = stringPreferencesKey("CURRENT_SESSION_ID")
    }

    override val currentPlayingSessionId: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PreferencesKey.CURRENT_SESSION_ID]
    }

    override suspend fun updateCurrentPlayingSession(sessionId: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.CURRENT_SESSION_ID] = sessionId
        }
    }
}
