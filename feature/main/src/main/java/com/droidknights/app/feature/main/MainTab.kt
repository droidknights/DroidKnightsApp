package com.droidknights.app.feature.main

import com.droidknights.app.feature.bookmark.navigation.BookmarkRoute
import com.droidknights.app.feature.home.navigation.HomeRoute
import com.droidknights.app.feature.setting.navigation.SettingRoute

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: String,
) {
    SETTING(
        iconResId = R.drawable.ic_setting,
        contentDescription = "설정",
        SettingRoute.ROUTE_SETTING,
    ),
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = "홈",
        HomeRoute.ROUTE,
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_bookmark,
        contentDescription = "북마크",
        BookmarkRoute.ROUTE,
    );

    companion object {

        operator fun contains(route: String): Boolean {
            return entries.map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return entries.find { it.route == route }
        }
    }
}
