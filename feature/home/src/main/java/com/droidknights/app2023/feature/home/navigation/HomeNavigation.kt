package com.droidknights.app2023.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.home.HomeScreen

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(HomeRoute.route, navOptions)
}

fun NavGraphBuilder.homeNavGraph() {
    composable(route = HomeRoute.route) {
        HomeScreen()
    }
}

object HomeRoute {
    const val route = "home"
} 
