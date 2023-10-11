package com.droidknights.app2023.feature.wearplayer.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.wear.compose.navigation.composable
import com.droidknights.app2023.feature.wearplayer.WearPlayerScreen

fun NavController.navigateWearPlayer(sessionId: String) {
    navigate(WearPlayerRoute.route(sessionId))
}

fun NavGraphBuilder.wearPlayerNavGraph() {
    composable(
        route = WearPlayerRoute.route("{${WearPlayerRoute.argumentName}}"),
        arguments = listOf(
            navArgument("sessionId") {
                type = NavType.StringType
                defaultValue = ""
            }
        ),
        deepLinks = listOf(
            navDeepLink { uriPattern = WearPlayerRoute.deepLinkUriPattern }
        ),
    ) {
        WearPlayerScreen()
    }
}

object WearPlayerRoute {
    const val route = "wear-player"
    fun route(sessionId: String = ""): String = "$route?$argumentName=$sessionId"
    const val argumentName = "sessionId"
    const val deepLinkUriPattern = "droidknights://$route"
}
