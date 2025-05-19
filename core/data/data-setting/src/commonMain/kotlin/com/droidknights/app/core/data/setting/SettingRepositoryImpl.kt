package com.droidknights.app.core.data.setting

import com.droidknights.app.core.data.setting.api.SettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class SettingRepositoryImpl : SettingRepository {

    // TODO datasource 연결하기
    private val _isDarkTheme = MutableStateFlow(false)

    override fun flowIsDarkTheme(): Flow<Boolean> =
        _isDarkTheme.asStateFlow()

    override suspend fun updateIsDarkTheme(isDarkTheme: Boolean) {
        _isDarkTheme.value = isDarkTheme
    }
}
