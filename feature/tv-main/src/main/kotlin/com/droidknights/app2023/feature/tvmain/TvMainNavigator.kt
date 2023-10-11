package com.droidknights.app2023.feature.tvmain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.droidknights.app2023.feature.player.navigation.navigatePlayer
import com.droidknights.app2023.feature.tvsession.navigation.TvSessionRoute

internal class TvMainNavigator(
    val navController: NavHostController,
) {
    val startDestination = TvSessionRoute.route

    fun navigatePlayer(sessionId: String) {
        navController.navigatePlayer(sessionId)
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}

@Composable
internal fun rememberTvMainNavigator(
    navController: NavHostController = rememberNavController(),
): TvMainNavigator = remember(navController) {
    TvMainNavigator(navController)
}
