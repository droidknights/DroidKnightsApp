package com.droidknights.app.feature.bookmark.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.feature.bookmark.BookmarkScreen

fun NavController.navigateBookmark() {
    navigate(MainTabRoute.Bookmark)
}

fun NavGraphBuilder.bookmarkNavGraph(
    onBackClick: () -> Unit,
) {
    composable<MainTabRoute.Bookmark> {
        BookmarkScreen(onBackClick = onBackClick)
    }
}
