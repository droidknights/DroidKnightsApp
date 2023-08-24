package com.droidknights.app2023.feature.bookmark.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.bookmark.BookmarkRoute

fun NavController.navigateBookmark(navOptions: NavOptions) {
    navigate(BookmarkRoute.route, navOptions)
}

fun NavGraphBuilder.bookmarkNavGraph(
    snackBarHostState: SnackbarHostState
) {
    composable(route = BookmarkRoute.route) {
        BookmarkRoute(snackBarHostState)
    }
}

object BookmarkRoute {
    const val route = "bookmark"
}
