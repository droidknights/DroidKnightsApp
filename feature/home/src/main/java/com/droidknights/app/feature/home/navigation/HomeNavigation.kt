package com.droidknights.app.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.feature.home.HomeRoute
import com.droidknights.app.feature.home.api.RouteHome

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<RouteHome> {
        HomeRoute(
            padding = padding,
            onShowErrorSnackBar = onShowErrorSnackBar,
        )
    }
}
