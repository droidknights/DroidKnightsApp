package com.droidknights.app2023.feature.main

import com.droidknights.app2023.feature.home.navigation.HomeRoute
import com.droidknights.app2023.feature.setting.navigation.SettingRoute

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: String,
) {
    SETTING(
        iconResId = R.drawable.ic_setting,
        contentDescription = "설정",
        SettingRoute.route,
    ),
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = "홈",
        HomeRoute.route,
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_bookmark,
        contentDescription = "북마크",
        "bookmark",
    );

    companion object {
        operator fun contains(route: String): Boolean {
            return values().map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return values().find { it.route == route }
        }
    }
}
