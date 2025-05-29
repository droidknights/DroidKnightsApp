package com.droidknights.app.core.data.setting.api

import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun isDarkTheme(): Flow<Boolean>

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean)
}
