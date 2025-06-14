package com.droidknights.app.feature.setting

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.action.api.FlowActionStream
import com.droidknights.app.core.action.api.onAction
import com.droidknights.app.core.data.settings.api.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor(
    private val flowActionStream: FlowActionStream,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    @VisibleForTesting
    val flowAction by lazy {
        flowActionStream.onAction<SettingAction>()
            .onEach {
                handleAction(action = it)
            }
    }

    fun loadAction(): SharedFlow<SettingAction> =
        flowAction
            .shareIn(viewModelScope, started = SharingStarted.WhileSubscribed())

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
