package com.droidknights.app2023.core.data.repository

import com.droidknights.app2023.core.datastore.datasource.SettingsPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultSettingsRepository @Inject constructor(
    private val preferencesDataSource: SettingsPreferencesDataSource
) : SettingsRepository {

    override fun getIsDarkTheme(): Flow<Boolean> =
        preferencesDataSource.settingsData.map { settingsData -> settingsData.isDarkTheme }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        preferencesDataSource.updateIsDarkTheme(isDarkTheme)
    }
}
