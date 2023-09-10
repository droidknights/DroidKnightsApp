package com.droidknights.app2023.feature.bookmark.navigation

import com.droidknights.app2023.feature.bookmark.R
import com.droidknights.app2023.feature.nav.DroidKnightsTab
import javax.inject.Inject

internal class BookmarkTab @Inject constructor() : DroidKnightsTab {
    override val iconResId: Int = R.drawable.ic_bookmark
    override val contentDescription: String = "북마크"
    override val route: String = BookmarkRoute.route
    override val order: Int = 2
    override val isStartDestination: Boolean = false
}
