package com.droidknights.app2023.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.droidknights.app2023.feature.home.navigation.HomeRoute
import com.droidknights.app2023.feature.home.navigation.navigateHome

internal class MainNavigator(
    startTab: MainTab = MainTab.HOME,
    val navController: NavHostController,
) {
    val startDestination: String = when (startTab) {
        MainTab.HOME -> HomeRoute.route
        MainTab.SETTING -> "setting"
        MainTab.TEMP -> "temp"
    }
    
    var currentTab: MainTab by mutableStateOf(startTab)
        private set

    fun navigate(tab: MainTab) {
        if (tab == currentTab) return
        when (tab) {
            MainTab.SETTING -> navController.navigate("setting") // TODO: navigate settings
            MainTab.HOME -> navController.navigateHome()
            MainTab.TEMP -> navController.navigate("temp") // TODO: ???
        }
        currentTab = tab
    }
}

@Composable
internal fun rememberMainNavigator(
    starTab: MainTab = MainTab.HOME,
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(starTab, navController)
}
