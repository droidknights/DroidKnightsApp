package com.droidknights.app.feature.main

import androidx.lifecycle.ViewModel
import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.feature.session.api.RouteSessionDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    settingsRepository: SettingsRepository,
    private val navigator: Navigator,
) : ViewModel() {

    val isDarkTheme = settingsRepository.flowIsDarkTheme()

    fun navigateSessionDetail(sessionId: String) = viewModelScope.launch {
        navigator.navigate(RouteSessionDetail(sessionId))
    }
}
