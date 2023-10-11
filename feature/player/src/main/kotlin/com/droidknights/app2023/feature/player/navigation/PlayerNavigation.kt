package com.droidknights.app2023.feature.player.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.droidknights.app2023.feature.player.PlayerScreen

fun NavController.navigatePlayer(sessionId: String) {
    navigate(PlayerRoute.route(sessionId))
}

fun NavGraphBuilder.playerNavGraph(
    onBackClick: () -> Unit,
) {
    composable(
        route = PlayerRoute.route("{${PlayerRoute.argumentName}}"),
        arguments = listOf(
            navArgument("sessionId") {
                type = NavType.StringType
                defaultValue = ""
            }
        ),
        deepLinks = listOf(
            navDeepLink { uriPattern = PlayerRoute.deepLinkUriPattern }
        ),
    ) {
        PlayerScreen(
            onBackClick = onBackClick
        )
    }
}

object PlayerRoute {
    const val route = "player"
    fun route(sessionId: String = ""): String = "$route?$argumentName=$sessionId"
    const val argumentName = "sessionId"
    const val deepLinkUriPattern = "droidknights://$route"
}
