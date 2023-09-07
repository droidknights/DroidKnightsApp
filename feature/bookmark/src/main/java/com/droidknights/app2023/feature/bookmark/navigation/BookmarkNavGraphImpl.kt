package com.droidknights.app2023.feature.bookmark.navigation

import androidx.navigation.NavGraphBuilder
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavGraph
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavGraphInfo
import javax.inject.Inject

internal class BookmarkNavGraphImpl @Inject constructor() : BookmarkNavGraph {
    override fun buildNavGraph(navGraphBuilder: NavGraphBuilder, navInfo: BookmarkNavGraphInfo) {
        navGraphBuilder.bookmarkNavGraph(navInfo.onShowErrorSnackBar)
    }
}
