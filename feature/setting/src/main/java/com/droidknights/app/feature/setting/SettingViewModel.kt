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
        navigator.navigate(Home, true, true)
    }

    fun navigateBookmark() = viewModelScope.launch {
        navigator.navigate(Bookmark, true, true)
    }
}
