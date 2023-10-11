package com.droidknights.app2023.feature.wearmain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.droidknights.app2023.feature.wearplayer.navigation.navigateWearPlayer
import com.droidknights.app2023.feature.wearsession.navigation.WearSessionRoute

internal class WearMainNavigator(
    val navController: NavHostController,
) {
    val startDestination = WearSessionRoute.route

    fun navigateWearPlayer(sessionId: String) {
        navController.navigateWearPlayer(sessionId)
    }
}

@Composable
internal fun rememberWearMainNavigator(
    navController: NavHostController = rememberSwipeDismissableNavController(),
): WearMainNavigator = remember(navController) {
    WearMainNavigator(navController)
}
