package com.droidknights.app.feature.contributor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.feature.contributor.ContributorRoute

fun NavController.navigateContributor() {
    this.navigate(ContributorRoute.ROUTE)
}

fun NavGraphBuilder.contributorNavGraph(
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = ContributorRoute.ROUTE) {
        ContributorRoute(
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object ContributorRoute {

    const val ROUTE = "contributor"
}
