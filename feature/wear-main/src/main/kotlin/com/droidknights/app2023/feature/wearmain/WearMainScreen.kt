package com.droidknights.app2023.feature.wearmain

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import com.droidknights.app2023.feature.wearplayer.navigation.wearPlayerNavGraph
import com.droidknights.app2023.feature.wearsession.navigation.wearSessionNavGraph

@Composable
internal fun WearMainScreen(
    navigator: WearMainNavigator = rememberWearMainNavigator()
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        // https://developer.android.com/training/wearables/compose/navigation
        SwipeDismissableNavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            wearSessionNavGraph(
                onSessionClick = { navigator.navigateWearPlayer(it.id) },
            )
            wearPlayerNavGraph()
        }
    }
}
