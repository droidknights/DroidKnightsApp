package com.droidknights.app.feature.session.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.droidknights.app.feature.session.SessionDetailScreen
import com.droidknights.app.feature.session.SessionScreen
import com.droidknights.app.feature.session.api.RouteSession
import com.droidknights.app.feature.session.api.RouteSessionDetail

fun NavGraphBuilder.sessionNavGraph() {
    composable<RouteSession> {
        SessionScreen()
    }

    composable<RouteSessionDetail> { navBackStackEntry ->
        val sessionId = navBackStackEntry.toRoute<RouteSessionDetail>().sessionId
        SessionDetailScreen(
            sessionId = sessionId,
        )
    }
}
