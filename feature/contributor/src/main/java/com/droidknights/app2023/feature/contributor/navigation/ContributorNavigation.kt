package com.droidknights.app2023.feature.contributor.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.contributor.ContributorRoute

fun NavController.navigateContributor() {
    this.navigate(ContributorRoute.route)
}

fun NavGraphBuilder.contributorNavGraph(
    onBackClick: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    composable(route = ContributorRoute.route) {
        ContributorRoute(
            onBackClick = onBackClick,
            snackBarHostState = snackBarHostState
        )
    }
}

object ContributorRoute {
    const val route = "contributor"
}
