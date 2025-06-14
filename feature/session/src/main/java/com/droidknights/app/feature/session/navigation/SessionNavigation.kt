package com.droidknights.app.feature.session.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.droidknights.app.feature.session.api.RouteSession
import com.droidknights.app.feature.session.api.RouteSessionDetail
import com.droidknights.app.feature.session.detail.SessionDetailScreen
import com.droidknights.app.feature.session.list.SessionScreen

fun NavGraphBuilder.sessionNavGraph(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<RouteSession> {
        val sessionId: String? = it.toRoute<RouteSession>().sessionId
        var scrollToEventConsumed by rememberSaveable { mutableStateOf(false) }
        SessionScreen(
            scrollToSessionId = if (scrollToEventConsumed) null else sessionId,
            onSessionClick = { session ->
                scrollToEventConsumed = true
            },
            onShowErrorSnackBar = onShowErrorSnackBar,
        )
    }

    composable<RouteSessionDetail> { navBackStackEntry ->
        val sessionId = navBackStackEntry.toRoute<RouteSessionDetail>().sessionId
        SessionDetailScreen(sessionId = sessionId)
    }
}
