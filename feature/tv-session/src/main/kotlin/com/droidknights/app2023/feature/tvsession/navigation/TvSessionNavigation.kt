package com.droidknights.app2023.feature.tvsession.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.feature.tvsession.TvSessionScreen

fun NavController.navigateTvSession() {
    navigate(TvSessionRoute.route)
}

fun NavGraphBuilder.tvSessionNavGraph(
    onSessionClick: (Session) -> Unit,
) {
    composable(TvSessionRoute.route) {
        TvSessionScreen(onSessionClick = onSessionClick)
    }
}

object TvSessionRoute {
    const val route: String = "tv-session"
}
