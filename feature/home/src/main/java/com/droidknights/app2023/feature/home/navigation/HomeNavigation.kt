package com.droidknights.app2023.feature.home.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.droidknights.app2023.feature.home.HomeScreen

fun NavController.navigateHome() {
    navigate(HomeRoute.route)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onContributorClick: () -> Unit,
) {
    composable(route = HomeRoute.route) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(padding)
        ) {
            HomeScreen(onContributorClick)
        }
    }
}

object HomeRoute {
    const val route = "home"
} 
