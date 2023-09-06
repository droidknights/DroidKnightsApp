package com.droidknights.app2023.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.droidknights.app2023.core.datastore.api.datasource.SettingsPreferencesDataSource
import com.droidknights.app2023.core.datastore.api.model.SettingsData
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

internal class DefaultSettingsPreferencesDataSource @Inject constructor(
    @Named("setting") private val dataStore: DataStore<Preferences>,
) : SettingsPreferencesDataSource {
    object PreferencesKey {
        val IS_DARK_THEME = booleanPreferencesKey("IS_DARK_THEME")
    }

    override val settingsData = dataStore.data.map { preferences ->
        SettingsData(
            isDarkTheme = preferences[PreferencesKey.IS_DARK_THEME] ?: false
        )
    }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.IS_DARK_THEME] = isDarkTheme
        }
    }
}
