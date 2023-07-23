package com.droidknights.app2023.feature.session.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.session.SessionScreen

fun NavController.navigateSession() {
    navigate(SessionRoute.route)
}

fun NavGraphBuilder.sessionNavGraph() {
    composable(SessionRoute.route) {
        SessionScreen()
    }
}

object SessionRoute {
    val route: String = "session"
}
