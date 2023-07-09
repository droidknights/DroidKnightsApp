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

internal class MainNavigator(
    private val navController: NavHostController,
    private val homeNavigation: HomeNavigation,
) {
    private val startTab = MainTab.HOME
    
    val startDestination = startTab.route
    var currentTab by mutableStateOf(startTab)
        private set
    
    @Composable
    fun content() {
        NavHost(
            navController = navController,
            startDestination = startDestination,
        ) {
            homeNavigation.content(this)
            
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
        if (tab == currentTab) {
            return
        }
        currentTab = tab
        navController.navigate(tab.route)
    }
    
    private val MainTab.route: String
        get() = when (this) {
            MainTab.SETTING -> "setting"
            MainTab.HOME -> homeNavigation.route
            MainTab.TEMP -> "temp"
        }
}

@Composable
internal fun rememberMainNavigator(
    homeNavigation: HomeNavigation,
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController, homeNavigation) {
    MainNavigator(navController, homeNavigation)
}
