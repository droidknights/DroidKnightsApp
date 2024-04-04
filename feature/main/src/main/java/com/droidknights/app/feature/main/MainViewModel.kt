package com.droidknights.app.feature.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.data.repository.SettingsRepository
import com.droidknights.app.widget.sendWidgetUpdateCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    val isDarkTheme = settingsRepository.getIsDarkTheme()

    init {
        sendWidgetUpdateCommand(application)
    }

    fun updateIsDarkTheme(isDarkTheme: Boolean) = viewModelScope.launch {
        settingsRepository.updateIsDarkTheme(isDarkTheme)
    }
}
