package com.droidknights.app.feature.contributor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.Route
import com.droidknights.app.feature.contributor.ContributorScreen

fun NavController.navigateContributor() {
    this.navigate(Route.Contributor)
}

fun NavGraphBuilder.contributorNavGraph(
    onBackClick: () -> Unit,
) {
    composable<Route.Contributor> {
        ContributorScreen(onBackClick = onBackClick)
    }
}
