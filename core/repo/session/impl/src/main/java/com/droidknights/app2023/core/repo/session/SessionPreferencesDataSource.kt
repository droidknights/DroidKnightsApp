package com.droidknights.app2023.core.repo.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

internal class SessionPreferencesDataSource @Inject constructor(
    @Named(PreferencesKey.DATA_STORE) private val dataStore: DataStore<Preferences>,
) {
    object PreferencesKey {
        const val DATA_STORE = "session"
        val BOOKMARKED_SESSION = stringSetPreferencesKey("BOOKMARKED_SESSION")
    }

    val bookmarkedSession = dataStore.data.map { preferences ->
        preferences[PreferencesKey.BOOKMARKED_SESSION] ?: emptySet()
    }

    suspend fun updateBookmarkedSession(bookmarkedSession: Set<String>) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.BOOKMARKED_SESSION] = bookmarkedSession
        }
    }
}
