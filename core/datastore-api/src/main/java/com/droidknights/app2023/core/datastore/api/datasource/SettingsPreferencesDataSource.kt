package com.droidknights.app2023.core.datastore.api.datasource

import com.droidknights.app2023.core.datastore.api.model.SettingsData
import kotlinx.coroutines.flow.Flow

interface SettingsPreferencesDataSource {
    val settingsData: Flow<SettingsData>
    suspend fun updateIsDarkTheme(isDarkTheme: Boolean)
}
