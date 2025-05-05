package com.droidknights.app.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.feature.home.HomeScreen

fun NavController.navigateHome() {
    navigate(MainTabRoute.Home)
}

fun NavGraphBuilder.homeNavGraph(
    onBackClick: () -> Unit,
) {
    composable<MainTabRoute.Home> {
        HomeScreen(onBackClick = onBackClick)
    }
}
