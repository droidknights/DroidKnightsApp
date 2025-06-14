package com.droidknights.app.core.datastore.session.api

import kotlinx.coroutines.flow.Flow

interface SessionPreferencesDataSource {

    val bookmarkedSession: Flow<Set<String>>

    suspend fun updateBookmarkedSession(bookmarkedSession: Set<String>)
}
