package com.droidknights.app.feature.bookmark.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.feature.bookmark.BookmarkRoute

fun NavController.navigateBookmark(navOptions: NavOptions) {
    navigate(MainTabRoute.Bookmark, navOptions)
}

fun NavGraphBuilder.bookmarkNavGraph() {
    composable<MainTabRoute.Bookmark> {
        BookmarkRoute(onShowErrorSnackBar = { /* TODO : 스낵바 연결 필요 */ })
    }
}
