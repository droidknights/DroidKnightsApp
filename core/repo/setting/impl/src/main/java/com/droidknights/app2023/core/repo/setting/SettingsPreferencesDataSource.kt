package com.droidknights.app2023.core.repo.setting

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.droidknights.app2023.core.repo.setting.api.model.SettingsData
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

internal class SettingsPreferencesDataSource @Inject constructor(
    @Named(PreferencesKey.DATA_STORE) private val dataStore: DataStore<Preferences>,
) {
    object PreferencesKey {
        const val DATA_STORE = "setting"
        val IS_DARK_THEME = booleanPreferencesKey("IS_DARK_THEME")
    }

    val settingsData = dataStore.data.map { preferences ->
        SettingsData(
            isDarkTheme = preferences[PreferencesKey.IS_DARK_THEME] ?: false
        )
    }

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.IS_DARK_THEME] = isDarkTheme
        }
    }
}
