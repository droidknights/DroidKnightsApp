package com.droidknights.app.core.data.setting

import com.droidknights.app.core.data.setting.api.SettingRepository
import com.droidknights.app.core.datastore.settings.api.SettingsPreferenceDataSource
import kotlinx.coroutines.flow.Flow

internal class SettingRepositoryImpl(
    private val preferenceDataSource: SettingsPreferenceDataSource,
) : SettingRepository {

    override fun isDarkTheme(): Flow<Boolean> = preferenceDataSource.isDarkTheme

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        preferenceDataSource.updateIsDarkTheme(isDarkTheme)
    }
}
