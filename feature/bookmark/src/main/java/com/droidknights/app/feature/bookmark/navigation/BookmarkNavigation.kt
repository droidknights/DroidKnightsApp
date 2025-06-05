package com.droidknights.app.feature.bookmark.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app.core.navigation.MainTabRoute.Bookmark
import com.droidknights.app.feature.bookmark.BookmarkRoute

fun NavGraphBuilder.bookmarkNavGraph(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<Bookmark> {
        BookmarkRoute(
            onShowErrorSnackBar = onShowErrorSnackBar,
        )
    }
}
