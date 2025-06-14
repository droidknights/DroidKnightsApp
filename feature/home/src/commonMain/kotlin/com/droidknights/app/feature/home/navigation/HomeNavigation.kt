package com.droidknights.app.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.feature.home.HomeScreen

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    onContributorClick: () -> Unit,
    onOrganizationSponsorClick: (String) -> Unit,
    onMapClick: () -> Unit,
) {
    composable<MainTabRoute.Home> {
        HomeScreen(
            onContributorClick = onContributorClick,
            onOrganizationSponsorClick = onOrganizationSponsorClick,
            onMapClick = onMapClick,
        )
    }
}
