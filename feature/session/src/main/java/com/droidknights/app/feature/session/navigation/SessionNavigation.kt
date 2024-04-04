package com.droidknights.app.feature.session.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.droidknights.app.core.model.Session
import com.droidknights.app.feature.session.SessionDetailScreen
import com.droidknights.app.feature.session.SessionScreen

fun NavController.navigateSession() {
    navigate(SessionRoute.ROUTE)
}

fun NavController.navigateSessionDetail(sessionId: String) {
    navigate(SessionRoute.detailRoute(sessionId))
}

fun NavGraphBuilder.sessionNavGraph(
    onBackClick: () -> Unit,
    onSessionClick: (Session) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(SessionRoute.ROUTE) {
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

    const val ROUTE: String = "session"

    fun detailRoute(sessionId: String): String = "$ROUTE/$sessionId"
}
