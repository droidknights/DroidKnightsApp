package com.droidknights.app.feature.license

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.data.setting.api.SettingRepository
import com.droidknights.app.feature.license.model.SettingUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class LicenseViewModel(
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
