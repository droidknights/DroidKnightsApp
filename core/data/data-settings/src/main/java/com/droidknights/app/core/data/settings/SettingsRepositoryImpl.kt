package com.droidknights.app.core.data.settings

import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.datastore.datasource.SettingsPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val preferencesDataSource: SettingsPreferencesDataSource,
) : SettingsRepository {

    override fun flowIsDarkTheme(): Flow<Boolean> =
        preferencesDataSource.settingsData
            .map { settingsData ->
                settingsData.isDarkTheme
            }

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        preferencesDataSource.updateIsDarkTheme(isDarkTheme)
    }
}
