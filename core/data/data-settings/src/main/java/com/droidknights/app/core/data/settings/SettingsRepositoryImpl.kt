package com.droidknights.app.core.data.settings

import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.datastore.settings.api.SettingsPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val preferencesDataSource: SettingsPreferencesDataSource,
) : SettingsRepository {

    override fun flowIsDarkTheme(): Flow<Boolean> =
        preferencesDataSource.isDarkThemeFlow

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        preferencesDataSource.updateIsDarkTheme(isDarkTheme)
    }
}
