package com.droidknights.app.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.feature.bookmark.api.RouteBookmark
import com.droidknights.app.feature.home.api.RouteHome
import com.droidknights.app.feature.session.api.RouteSessionDetail
import com.droidknights.app.feature.setting.api.RouteSetting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    fun navigateSetting() = viewModelScope.launch {
        navigator.navigate(
            route = RouteSetting,
            saveState = true,
            launchSingleTop = true,
        )
    }

    fun navigateBookmark() = viewModelScope.launch {
        navigator.navigate(
            route = RouteBookmark,
            saveState = true,
            launchSingleTop = true,
        )
    }

    fun navigateHome() = viewModelScope.launch {
        navigator.navigate(
            route = RouteHome,
            saveState = true,
            launchSingleTop = true,
        )
    }
}
