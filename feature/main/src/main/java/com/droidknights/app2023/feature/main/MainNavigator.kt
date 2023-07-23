package com.droidknights.app2023.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.droidknights.app2023.feature.contributor.navigation.navigateContributor
import com.droidknights.app2023.feature.home.navigation.HomeRoute
import com.droidknights.app2023.feature.home.navigation.navigateHome
import com.droidknights.app2023.feature.session.navigation.navigateSession
import com.droidknights.app2023.feature.setting.navigation.SettingRoute
import com.droidknights.app2023.feature.setting.navigation.navigateSetting

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = HomeRoute.route

    private val MainTab.route: String
        get() = when (this) {
            MainTab.SETTING -> SettingRoute.route
            MainTab.HOME -> HomeRoute.route
            MainTab.TEMP -> "temp"
        }

    val currentTab: MainTab?
        @Composable get() = when (currentDestination?.route) {
            HomeRoute.route -> MainTab.HOME
            SettingRoute.route -> MainTab.SETTING
            "temp" -> MainTab.TEMP
            else -> null
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
            MainTab.TEMP -> navController.navigate("temp", navOptions) // TODO: ???
        }
    }

    fun navigateContributor() {
        navController.navigateContributor()
    }

    fun navigateSession() {
        navController.navigateSession()
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return isMainTabRoute(currentRoute)
    }

    private fun isMainTabRoute(route: String): Boolean {
        return route in MainTab.values().map { it.route }
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
