package com.droidknights.app.core.data.repository

import com.droidknights.app.core.datastore.datasource.SettingsPreferencesDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DefaultSettingsRepository @Inject constructor(
    private val preferencesDataSource: SettingsPreferencesDataSource
) : SettingsRepository {

    override fun getIsDarkTheme(): Flow<Boolean> =
        preferencesDataSource.settingsData.map { settingsData -> settingsData.isDarkTheme }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        preferencesDataSource.updateIsDarkTheme(isDarkTheme)
    }
}
