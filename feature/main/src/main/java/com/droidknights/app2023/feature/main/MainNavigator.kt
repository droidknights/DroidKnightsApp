package com.droidknights.app2023.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.droidknights.app2023.feature.contributor.navigation.ContributorRoute
import com.droidknights.app2023.feature.home.navigation.HomeRoute
import com.droidknights.app2023.feature.home.navigation.navigateHome
import com.droidknights.app2023.feature.setting.navigation.SettingRoute
import com.droidknights.app2023.feature.setting.navigation.navigateSetting

internal class MainNavigator(
    startTab: MainTab = MainTab.HOME,
    val navController: NavHostController,
) {
    val startDestination: String = when (startTab) {
        MainTab.HOME -> HomeRoute.route
        MainTab.SETTING -> SettingRoute.route
        MainTab.TEMP -> "temp"
    }

    var currentTab: MainTab by mutableStateOf(startTab)
        private set

    fun navigate(tab: MainTab) {
        if (tab == currentTab) return
        when (tab) {
            MainTab.SETTING -> navController.navigateSetting()
            MainTab.HOME -> navController.navigateHome()
            MainTab.TEMP -> navController.navigate("temp") // TODO: ???
        }
        currentTab = tab
    }

    fun navigateContributor() {
        navController.navigate(ContributorRoute.route)
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return when (navBackStackEntry?.destination?.route) {
            ContributorRoute.route -> false
            else -> true
        }
    }
}

@Composable
internal fun rememberMainNavigator(
    starTab: MainTab = MainTab.HOME,
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(starTab, navController)
}
