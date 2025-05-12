package com.droidknights.app.core.datastore.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.droidknights.app.core.datastore.settings.api.SettingsPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultSettingsPreferencesDataSource @Inject constructor(
    @Named("setting") private val dataStore: DataStore<Preferences>,
) : SettingsPreferencesDataSource {

    override val isDarkThemeFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_IS_DARK_THEME] ?: false
    }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_IS_DARK_THEME] = isDarkTheme
        }
    }

    companion object {
        private val KEY_IS_DARK_THEME = booleanPreferencesKey("IS_DARK_THEME")
    }
}
