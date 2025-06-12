package com.droidknights.app.feature.bookmark.navigation

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute.Bookmark
import com.droidknights.app.core.router.api.model.Route
import com.droidknights.app.feature.bookmark.BookmarkRoute

fun NavGraphBuilder.bookmarkNavGraph(
    selectedTabRoute: State<Route>,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<Bookmark> {
        BookmarkRoute(
            selectedTabRoute = selectedTabRoute,
            onShowErrorSnackBar = onShowErrorSnackBar,
        )
    }
}
