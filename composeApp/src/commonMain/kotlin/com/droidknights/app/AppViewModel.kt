package com.droidknights.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.data.setting.api.SettingRepository
import com.droidknights.app.model.AppUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class AppViewModel(
    settingRepository: SettingRepository,
) : ViewModel() {

    val uiState = settingRepository
        .isDarkTheme()
        .map { AppUiState(isDarkTheme = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AppUiState(isDarkTheme = false),
        )
}
