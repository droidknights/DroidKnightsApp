package com.droidknights.app.feature.main

import androidx.compose.runtime.Composable
import com.droidknights.app.core.navigation.Bookmark
import com.droidknights.app.core.navigation.Home
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.core.navigation.Route
import com.droidknights.app.core.navigation.Setting

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: MainTabRoute,
) {
    SETTING(
        iconResId = R.drawable.ic_setting,
        contentDescription = "설정",
        Setting,
    ),
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = "홈",
        Home
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_bookmark,
        contentDescription = "북마크",
        Bookmark,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
