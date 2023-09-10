package com.droidknights.app2023.feature.nav

import androidx.navigation.NavController

interface DroidKnightsNavController<T> {
    fun route(): String = ""
    fun navigate(navController: NavController, navInfo: T)
}
