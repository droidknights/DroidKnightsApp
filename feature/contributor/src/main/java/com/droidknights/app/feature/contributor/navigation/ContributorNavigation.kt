package com.droidknights.app.feature.contributor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.Contributor
import com.droidknights.app.feature.contributor.ContributorRoute

fun NavController.navigateContributor() {
    this.navigate(Contributor)
}

fun NavGraphBuilder.contributorNavGraph(
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<Contributor> {
        ContributorRoute(
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}
