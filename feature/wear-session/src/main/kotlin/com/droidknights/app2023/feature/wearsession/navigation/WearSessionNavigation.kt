package com.droidknights.app2023.feature.wearsession.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.wear.compose.navigation.composable
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.feature.wearsession.WearSessionScreen

fun NavController.navigateWearSession() {
    navigate(WearSessionRoute.route)
}

fun NavGraphBuilder.wearSessionNavGraph(
    onSessionClick: (Session) -> Unit,
) {
    composable(WearSessionRoute.route) {
        WearSessionScreen(onSessionClick = onSessionClick)
    }
}

object WearSessionRoute {
    const val route: String = "wear-session"
}
