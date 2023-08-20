package com.droidknights.app2023.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app2023.core.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    val isDarkTheme = settingsRepository.getIsDarkTheme()

    fun updateIsDarkTheme(isDarkTheme: Boolean) = viewModelScope.launch {
        settingsRepository.updateIsDarkTheme(isDarkTheme)
    }
}
