package com.droidknights.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.feature.session.api.RouteSession
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val navigator: Navigator,
) : ViewModel() {

    fun navigateSession() = viewModelScope.launch {
        navigator.navigate(RouteSession)
    }
}
