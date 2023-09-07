package com.droidknights.app2023.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.home.HomeRoute

internal fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(HomeRoute.route, navOptions)
}

internal fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onSessionClick: () -> Unit,
    onContributorClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = HomeRoute.route) {
        HomeRoute(padding, onSessionClick, onContributorClick, onShowErrorSnackBar)
    }
}

internal object HomeRoute {
    const val route = "home"
}
