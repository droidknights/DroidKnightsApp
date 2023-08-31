package com.droidknights.app2023.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class SessionPreferencesDataSource @Inject constructor(
    @Named("session") private val dataStore: DataStore<Preferences>
) {
    object PreferencesKey {
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
