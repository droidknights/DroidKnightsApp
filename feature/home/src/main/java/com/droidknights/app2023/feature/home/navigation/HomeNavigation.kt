package com.droidknights.app2023.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.home.HomeRoute

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(HomeRoute.route, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onSessionClick: () -> Unit,
    onContributorClick: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    composable(route = HomeRoute.route) {
        HomeRoute(padding, onSessionClick, onContributorClick, snackBarHostState)
    }
}

object HomeRoute {
    const val route = "home"
}
