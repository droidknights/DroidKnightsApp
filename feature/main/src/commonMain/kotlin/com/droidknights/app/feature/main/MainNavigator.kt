package com.droidknights.app.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.core.navigation.Route
import com.droidknights.app.feature.bookmark.navigation.navigateBookmark
import com.droidknights.app.feature.contributor.navigation.navigateContributor
import com.droidknights.app.feature.home.navigation.navigateHome
import com.droidknights.app.feature.license.navigation.navigateLicense
import com.droidknights.app.feature.session.navigation.navigateSession
import com.droidknights.app.feature.session.navigation.navigateSessionDetail
import com.droidknights.app.feature.setting.navigation.navigateSetting

internal class MainNavigator(
    val navController: NavHostController,
    val uriHandler: UriHandler,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.HOME.route

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

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

    fun navigateLicense() {
        navController.navigateLicense()
    }

    fun navigateWeb(url: String) {
        uriHandler.openUri(url)
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination<MainTabRoute.Home>()) {
            popBackStack()
        }
    }
    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
    uriHandler: UriHandler = LocalUriHandler.current,
): MainNavigator = remember(navController) {
    MainNavigator(navController, uriHandler)
}
