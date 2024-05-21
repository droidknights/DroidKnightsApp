package com.droidknights.app.core.data.repository.api

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun flowIsDarkTheme(): Flow<Boolean>

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean)
}
