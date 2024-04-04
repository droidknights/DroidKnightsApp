package com.droidknights.app.core.data.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getIsDarkTheme(): Flow<Boolean>

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean)
}
