package com.droidknights.app.core.datastore.settings.api

import kotlinx.coroutines.flow.Flow

interface SettingsPreferenceDataSource {

    val isDarkThemeFlow: Flow<Boolean>

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean)
}
