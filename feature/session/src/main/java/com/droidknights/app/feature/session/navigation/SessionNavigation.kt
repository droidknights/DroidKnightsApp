package com.droidknights.app.feature.session.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.droidknights.app.feature.session.api.RouteSession
import com.droidknights.app.feature.session.api.RouteSessionDetail
import com.droidknights.app.feature.session.detail.SessionDetailScreen
import com.droidknights.app.feature.session.list.SessionScreen

fun NavController.navigateSessionDetail(sessionId: String) {
    navigate(RouteSessionDetail(sessionId))
}

fun NavGraphBuilder.sessionNavGraph(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<RouteSession> {
        SessionScreen(onShowErrorSnackBar = onShowErrorSnackBar)
    }

    composable<RouteSessionDetail> { navBackStackEntry ->
        val sessionId = navBackStackEntry.toRoute<RouteSessionDetail>().sessionId
        SessionDetailScreen(sessionId = sessionId)
    }
}
