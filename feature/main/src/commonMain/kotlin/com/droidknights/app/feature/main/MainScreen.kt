package com.droidknights.app.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.main.components.MainBottomBar
import com.droidknights.app.feature.main.components.MainNavHost

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navigator = rememberMainNavigator()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = KnightsTheme.colorScheme.background,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MainNavHost(navigator)

            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) },
            )
        }
    }
}
