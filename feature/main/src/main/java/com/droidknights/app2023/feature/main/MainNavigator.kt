package com.droidknights.app2023.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidknights.app2023.core.navigation.HomeNavigation
import com.droidknights.app2023.core.navigation.MainDestination

internal class MainNavigator(
    private val navController: NavHostController,
    private val homeNavigation: HomeNavigation,
) {
    private val startDestination: MainDestination = MainDestination.Home
    private var currentDestination: MainDestination by mutableStateOf(startDestination)
    
    val currentTab: MainTab
        get() = when (currentDestination) {
            MainDestination.Home -> MainTab.HOME
            MainDestination.Setting -> MainTab.SETTING
            MainDestination.Temp -> MainTab.TEMP
        }
    
    @Composable
    fun Content() {
        NavHost(
            navController = navController,
            startDestination = startDestination.route,
        ) {
            composable(MainDestination.Home.route) {
                with(homeNavigation) {
                    Content()
                }
            }
            
            // TODO: 각 모듈로 이동
            val content: @Composable (String) -> Unit = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    content = { Text(text = it) }
                )
            }
            composable("setting") { content("setting") }
            composable("temp") { content("temp") }
        }
    }
    
    fun navigate(tab: MainTab) {
        val destination = when (tab) {
            MainTab.SETTING -> MainDestination.Setting
            MainTab.HOME -> MainDestination.Home
            MainTab.TEMP -> MainDestination.Temp
        }
        navigate(destination)
    }
    
    private fun navigate(destination: MainDestination) {
        if (destination == currentDestination) {
            return
        }
        currentDestination = destination
        navController.navigate(destination.route)
    }
}

@Composable
internal fun rememberMainNavigator(
    homeNavigation: HomeNavigation,
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController, homeNavigation) {
    MainNavigator(navController, homeNavigation)
}
