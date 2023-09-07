package com.droidknights.app2023.feature.bookmark.navigation

import androidx.navigation.NavController
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavController
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavControllerInfo
import javax.inject.Inject

internal class BookmarkNavControllerImpl @Inject constructor() : BookmarkNavController {
    override fun route(): String = BookmarkRoute.route

    override fun navigate(navController: NavController, navInfo: BookmarkNavControllerInfo) {
        navController.navigateBookmark(navInfo.navOptions)
    }
}
