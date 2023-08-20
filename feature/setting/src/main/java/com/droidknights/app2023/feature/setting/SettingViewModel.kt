package com.droidknights.app2023.feature.setting

import androidx.lifecycle.ViewModel
import com.droidknights.app2023.core.navigation.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : ViewModel() {
    fun moveToLicense() = navigationProvider.toLicense()
}