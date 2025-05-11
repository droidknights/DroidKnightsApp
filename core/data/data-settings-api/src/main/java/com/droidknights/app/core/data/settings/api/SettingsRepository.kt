package com.droidknights.app.core.data.settings.api

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun flowIsDarkTheme(): Flow<Boolean>

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean)
}
