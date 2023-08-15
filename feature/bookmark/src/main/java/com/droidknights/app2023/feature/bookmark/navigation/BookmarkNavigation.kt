package com.droidknights.app2023.feature.bookmark.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

fun NavController.navigateBookmark(navOptions: NavOptions) {
    navigate(BookmarkRoute.route, navOptions)
}

fun NavGraphBuilder.bookmarkNavGraph() {
    composable(route = BookmarkRoute.route) {
        // TODO: BookmarkRoute 연결 필요
    }
}

object BookmarkRoute {
    const val route = "bookmark"
}
