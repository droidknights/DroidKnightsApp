package com.droidknights.app.feature.session.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
        val sessionId: String? = it.toRoute<RouteSession>().sessionId
        var scrollToEventConsumed by rememberSaveable { mutableStateOf(false) }
        SessionScreen(
            scrollToSessionId = if (scrollToEventConsumed) null else sessionId,
            onBackClick = onBackClick,
            onSessionClick = { session ->
                scrollToEventConsumed = true
                onSessionClick(session)
            },
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
