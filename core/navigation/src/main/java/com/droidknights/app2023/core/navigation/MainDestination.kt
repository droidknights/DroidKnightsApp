package com.droidknights.app2023.core.navigation

sealed class MainDestination(val route: String) {
    object Home : MainDestination("home")
    
    object Setting : MainDestination("setting")
    
    object Temp : MainDestination("temp")
}
