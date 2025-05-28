package com.droidknights.app.core.datastore.session

import com.droidknights.app.core.datastore.core.LocalPreferences
import com.droidknights.app.core.datastore.session.api.SessionPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DefaultSessionPreferencesDataSource(
    private val localPreferences: LocalPreferences,
) : SessionPreferencesDataSource {

    override val bookmarkedSession: Flow<Set<String>> = localPreferences
        .getStringSet(KEY_BOOKMARKED_SESSION)
        .map { it ?: emptySet() }

    override suspend fun updateBookmarkedSession(bookmarkedSession: Set<String>) {
        localPreferences.setStringSet(KEY_BOOKMARKED_SESSION, bookmarkedSession)
    }

    companion object {
        private const val KEY_BOOKMARKED_SESSION = "BOOKMARKED_SESSION"
    }
}
