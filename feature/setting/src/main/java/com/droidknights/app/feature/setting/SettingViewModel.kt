package com.droidknights.app.feature.setting

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.action.FlowActionStream
import com.droidknights.app.core.data.settings.api.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor(
    private val flowActionStream: FlowActionStream,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    @VisibleForTesting
    var actionJob: Job? = null

    @VisibleForTesting
    val flowAction by lazy {
        flowActionStream.flowAction()
            .filterIsInstance(SettingAction::class)
            .onEach {
                handleAction(action = it)
            }
    }

    fun loadAction() {
        actionJob?.cancel()

        actionJob = flowAction.launchIn(viewModelScope)
    }

    private suspend fun handleAction(action: SettingAction) {
        when (action) {
            is SettingAction.ChangeDarkTheme -> {
                settingsRepository.updateIsDarkTheme(action.isDarkTheme)
            }
        }
    }

    fun send(action: SettingAction) {
        flowActionStream.nextAction(action)
    }
}

package com.droidknights.app.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.navigation.MainTabRoute.Bookmark
import com.droidknights.app.core.navigation.MainTabRoute.Home
import com.droidknights.app.core.router.api.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {

    fun navigateHome() = viewModelScope.launch {
        navigator.navigate(
            route = Home,
            saveState = Home.saveState,
            launchSingleTop = Home.launchSingleTop,
        )
    }

    fun navigateBookmark() = viewModelScope.launch {
        navigator.navigate(
            route = Bookmark,
            saveState = Bookmark.saveState,
            launchSingleTop = Bookmark.launchSingleTop,
        )
    }
}
