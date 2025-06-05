package com.droidknights.app.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.feature.session.api.RouteSessionDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val navigator: Navigator,
) : ViewModel() {

    val isDarkTheme = settingsRepository.flowIsDarkTheme()

    fun updateIsDarkTheme(isDarkTheme: Boolean) =
        viewModelScope.launch {
            settingsRepository.updateIsDarkTheme(isDarkTheme)
        }

    fun navigateSessionDetail(sessionId: String) = viewModelScope.launch {
        navigator.navigate(RouteSessionDetail(sessionId))
    }
}
