package com.droidknights.app.feature.main

import androidx.compose.runtime.Composable
import com.droidknights.app.core.router.api.model.Route
import com.droidknights.app.feature.bookmark.api.RouteBookmark
import com.droidknights.app.feature.home.api.RouteHome
import com.droidknights.app.feature.setting.api.RouteSetting

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: Route,
) {
    SETTING(
        iconResId = R.drawable.ic_setting,
        contentDescription = "설정",
        route = RouteSetting,
    ),
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = "홈",
        route = RouteHome,
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_bookmark,
        contentDescription = "북마크",
        route = RouteBookmark,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (Route) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
