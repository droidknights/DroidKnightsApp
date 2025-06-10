package com.droidknights.app.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute.Home
import com.droidknights.app.core.router.api.model.Route
import com.droidknights.app.feature.home.HomeRoute

fun NavGraphBuilder.homeNavGraph(
    selectedTabRoute: State<Route>,
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<Home> {
        HomeRoute(
            selectedTabRoute = selectedTabRoute,
            padding = padding,
            onShowErrorSnackBar = onShowErrorSnackBar,
        )
    }
}
