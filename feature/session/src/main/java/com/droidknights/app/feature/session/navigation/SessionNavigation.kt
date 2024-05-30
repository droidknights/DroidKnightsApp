package com.droidknights.app.feature.session.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.droidknights.app.core.model.Session
import com.droidknights.app.core.navigation.SessionDetail
import com.droidknights.app.feature.session.SessionDetailScreen
import com.droidknights.app.feature.session.SessionScreen
import com.droidknights.app.core.navigation.Session as SessionRoute

fun NavController.navigateSession() {
    navigate(SessionRoute)
}

fun NavController.navigateSessionDetail(sessionId: String) {
    navigate(SessionDetail(sessionId))
}

fun NavGraphBuilder.sessionNavGraph(
    onBackClick: () -> Unit,
    onSessionClick: (Session) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<SessionRoute> {
        SessionScreen(
            onBackClick = onBackClick,
            onSessionClick = onSessionClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }

    composable<SessionDetail> { navBackStackEntry ->
        val sessionId = navBackStackEntry.toRoute<SessionDetail>().sessionId
        SessionDetailScreen(
            sessionId = sessionId,
            onBackClick = onBackClick
        )
    }
}
