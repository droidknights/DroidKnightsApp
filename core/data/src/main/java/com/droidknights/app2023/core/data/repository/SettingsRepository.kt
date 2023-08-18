package com.droidknights.app2023.core.data.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getIsDarkTheme(): Flow<Boolean>

    suspend fun updateIsDarkTheme(isDarkTheme: Boolean)
}
