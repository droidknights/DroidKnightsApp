package com.droidknights.app.core.datastore.settings

import com.droidknights.app.core.datastore.core.LocalPreferences
import com.droidknights.app.core.datastore.settings.api.SettingsPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DefaultSettingsPreferencesDataSource(
    private val localPreferences: LocalPreferences,
) : SettingsPreferenceDataSource {

    override val isDarkThemeFlow: Flow<Boolean> = localPreferences
        .getBoolean(KEY_IS_DARK_THEME)
        .map { it ?: false }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        localPreferences.setBoolean(KEY_IS_DARK_THEME, isDarkTheme)
    }

    companion object {
        private const val KEY_IS_DARK_THEME = "IS_DARK_THEME"
    }
}
