package com.droidknights.app.feature.contributor.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.feature.contributor.ContributorRoute
import com.droidknights.app.feature.contributor.api.RouteContributor

fun NavGraphBuilder.contributorNavGraph(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<RouteContributor> {
        ContributorRoute(
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}
