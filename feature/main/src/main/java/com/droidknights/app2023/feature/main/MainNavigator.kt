package com.droidknights.app2023.feature.main

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.droidknights.app2023.feature.bookmark.navigation.navigateBookmark
import com.droidknights.app2023.feature.contributor.navigation.navigateContributor
import com.droidknights.app2023.feature.home.navigation.navigateHome
import com.droidknights.app2023.feature.player.navigation.PlayerRoute
import com.droidknights.app2023.feature.player.navigation.navigatePlayer
import com.droidknights.app2023.feature.session.navigation.navigateSession
import com.droidknights.app2023.feature.session.navigation.navigateSessionDetail
import com.droidknights.app2023.feature.setting.navigation.navigateSetting

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.HOME.route

    val currentTab: MainTab?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainTab::find)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.SETTING -> navController.navigateSetting(navOptions)
            MainTab.HOME -> navController.navigateHome(navOptions)
            MainTab.BOOKMARK -> navController.navigateBookmark(navOptions)
        }
    }

    fun navigateContributor() {
        navController.navigateContributor()
    }

    fun navigateSession() {
        navController.navigateSession()
    }

    fun navigateSessionDetail(sessionId: String) {
        navController.navigateSessionDetail(sessionId)
    }

    fun navigatePlayer(sessionId: String) {
        navController.navigatePlayer(sessionId)
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in MainTab
    }

    @Composable
    fun shouldShowSystemUI(): Boolean {
        val orientation = LocalConfiguration.current.orientation
        val currentRoute = currentDestination?.route ?: return true
        return !currentRoute.startsWith(PlayerRoute.route) ||
                orientation == ORIENTATION_PORTRAIT
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
