package com.droidknights.app.feature.bookmark.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.feature.bookmark.BookmarkRoute
import com.droidknights.app.feature.session.api.RouteSession

fun NavController.navigateBookmark(navOptions: NavOptions) {
    navigate(MainTabRoute.Bookmark, navOptions)
}

fun NavController.navigateSession(sessionId: String) {
    navigate(RouteSession(sessionId))
}

fun NavGraphBuilder.bookmarkNavGraph(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onSessionClick: (Session) -> Unit,
) {
    composable<MainTabRoute.Bookmark> {
        BookmarkRoute(
            onShowErrorSnackBar = onShowErrorSnackBar,
            onSessionClick = onSessionClick,
        )
    }
}
