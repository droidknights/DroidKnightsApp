package com.droidknights.app2023.feature.session.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.feature.session.SessionDetailScreen
import com.droidknights.app2023.feature.session.SessionScreen

fun NavController.navigateSession() {
    navigate(SessionRoute.route)
}

fun NavController.navigateSessionDetail(sessionId: String) {
    navigate(SessionRoute.detailRoute(sessionId))
}

fun NavGraphBuilder.sessionNavGraph(
    onBackClick: () -> Unit,
    onSessionClick: (Session) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable(SessionRoute.route) {
        SessionScreen(
            onBackClick = onBackClick,
            onSessionClick = onSessionClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }

    composable(
        route = SessionRoute.detailRoute("{id}"),
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val sessionId = navBackStackEntry.arguments?.getString("id") ?: ""
        SessionDetailScreen(
            sessionId = sessionId,
            onBackClick = onBackClick
        )
    }
}

object SessionRoute {
    const val route: String = "session"

    fun detailRoute(sessionId: String): String = "$route/$sessionId"
}
