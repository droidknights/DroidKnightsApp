package com.droidknights.app2023.feature.tvmain

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.NonInteractiveSurfaceDefaults
import androidx.tv.material3.Surface
import com.droidknights.app2023.feature.player.navigation.playerNavGraph
import com.droidknights.app2023.feature.tvsession.navigation.tvSessionNavGraph

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun TvMainScreen(
    navigator: TvMainNavigator = rememberTvMainNavigator()
) {
    Surface(
        colors = NonInteractiveSurfaceDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            tvSessionNavGraph(
                onSessionClick = { navigator.navigatePlayer(it.id) },
            )
            playerNavGraph(
                onBackClick = { navigator.popBackStack() },
            )
        }
    }
}
