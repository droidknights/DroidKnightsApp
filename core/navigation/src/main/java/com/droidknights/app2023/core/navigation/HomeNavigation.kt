package com.droidknights.app2023.core.navigation

import androidx.navigation.NavGraphBuilder

interface HomeNavigation {
    val route: String
    
    fun content(builder: NavGraphBuilder)
}
