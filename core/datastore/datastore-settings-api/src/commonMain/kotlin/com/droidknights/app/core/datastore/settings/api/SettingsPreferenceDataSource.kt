package com.droidknights.app.core.datastore.settings.api

import kotlinx.coroutines.flow.Flow

interface SettingsPreferenceDataSource {

    val isDarkTheme: Flow<Boolean>

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean)
}
