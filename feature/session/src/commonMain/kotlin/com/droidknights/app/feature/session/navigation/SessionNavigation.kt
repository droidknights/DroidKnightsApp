package com.droidknights.app.feature.session.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.droidknights.app.core.navigation.Route
import com.droidknights.app.feature.session.SessionDetailScreen
import com.droidknights.app.feature.session.SessionScreen
import com.droidknights.app.core.navigation.Route.Session as SessionRoute

fun NavController.navigateSession() {
    navigate(SessionRoute)
}

fun NavController.navigateSessionDetail(sessionId: String) {
    navigate(Route.SessionDetail(sessionId))
}

fun NavGraphBuilder.sessionNavGraph(
    onBackClick: () -> Unit,
    onSessionClick: (String) -> Unit
) {
    composable<SessionRoute> {
        SessionScreen(
            onBackClick = onBackClick,
            onSessionClick = onSessionClick
        )
    }

    composable<Route.SessionDetail> { navBackStackEntry ->
        val sessionId = navBackStackEntry.toRoute<Route.SessionDetail>().sessionId
        SessionDetailScreen(
            sessionId = sessionId,
            onBackClick = onBackClick
        )
    }
}
