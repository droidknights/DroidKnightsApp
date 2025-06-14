package com.droidknights.app.feature.bookmark.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.feature.bookmark.BookmarkRoute
import com.droidknights.app.feature.bookmark.api.RouteBookmark

fun NavGraphBuilder.bookmarkNavGraph(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<RouteBookmark> {
        BookmarkRoute(
            onShowErrorSnackBar = onShowErrorSnackBar,
        )
    }
}
