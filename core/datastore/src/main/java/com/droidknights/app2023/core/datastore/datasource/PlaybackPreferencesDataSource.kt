package com.droidknights.app2023.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.droidknights.app2023.core.datastore.model.PlaybackData
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class PlaybackPreferencesDataSource @Inject constructor(
    @Named("playback") private val dataStore: DataStore<Preferences>
) {
    object PreferencesKey {
        val CURRENT_SESSION_ID = stringPreferencesKey("CURRENT_SESSION_ID")
    }

    val playbackData = dataStore.data.map { preferences ->
        preferences[PreferencesKey.CURRENT_SESSION_ID]?.let(::PlaybackData)
    }

    suspend fun updateCurrentSessionId(sessionId: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.CURRENT_SESSION_ID] = sessionId
        }
    }
}
