package com.droidknights.app2023.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app2023.core.navigation.HomeNavigation
import com.droidknights.app2023.feature.home.HomeScreen

internal class HomeNavigationImpl : HomeNavigation {
    
    override val route: String = ROUTE
    
    override fun content(builder: NavGraphBuilder) = with(builder) {
        composable(ROUTE) {
            HomeScreen()
        }
    }
    
    companion object {
        private const val ROUTE = "home"
    }
}
