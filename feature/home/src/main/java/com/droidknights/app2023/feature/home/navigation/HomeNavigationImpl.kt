package com.droidknights.app2023.feature.home.navigation

import androidx.compose.runtime.Composable
import com.droidknights.app2023.core.navigation.HomeNavigation
import com.droidknights.app2023.feature.home.HomeScreen
import javax.inject.Inject

internal class HomeNavigationImpl @Inject constructor() : HomeNavigation {
    
    @Composable
    override fun Content() {
        HomeScreen()
    }
}
