package com.droidknights.app.feature.session.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.feature.session.api.RouteSession
import com.droidknights.app.feature.session.api.RouteSessionDetail
import com.droidknights.app.feature.session.detail.SessionDetailScreen
import com.droidknights.app.feature.session.list.SessionScreen

fun NavController.navigateSessionDetail(sessionId: String) {
    navigate(RouteSessionDetail(sessionId))
}

fun NavGraphBuilder.sessionNavGraph(
    onBackClick: () -> Unit,
    onSessionClick: (Session) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<RouteSession> {
        SessionScreen(
            onBackClick = onBackClick,
            onSessionClick = onSessionClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }

    composable<RouteSessionDetail> { navBackStackEntry ->
        val sessionId = navBackStackEntry.toRoute<RouteSessionDetail>().sessionId
        SessionDetailScreen(
            sessionId = sessionId,
            onBackClick = onBackClick,
        )
    }
}
