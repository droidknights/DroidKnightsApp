package com.droidknights.app.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.data.setting.api.SettingRepository
import com.droidknights.app.feature.setting.model.SettingUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class SettingViewModel(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    val uiState = settingRepository
        .flowIsDarkTheme()
        .map { SettingUiState(isDarkTheme = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SettingUiState(isDarkTheme = false),
        )

    fun updateDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch {
            settingRepository.updateIsDarkTheme(isDarkTheme)
        }
    }
}
