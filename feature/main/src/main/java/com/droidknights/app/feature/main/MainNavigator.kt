package com.droidknights.app.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

internal class MainNavigator(
    val navController: NavHostController,
    private val onTabClick: (tab: MainTab, saveState: Boolean, launchSingleTop: Boolean) -> Unit,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.HOME.route

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) = onTabClick(tab, SAVE_STATE, LAUNCH_SINGLE_TOP)

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    companion object {
        private const val SAVE_STATE = true
        private const val LAUNCH_SINGLE_TOP = true
    }
}

@Composable
internal fun rememberMainNavigator(
    onTabClick: (tab: MainTab, saveState: Boolean, launchSingleTop: Boolean) -> Unit,
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController, onTabClick)
}
