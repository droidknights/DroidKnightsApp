package com.droidknights.app.feature.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.main.components.DeviceFrame
import com.droidknights.app.feature.main.components.ProjectDescription

@Composable
actual fun MainScreen(modifier: Modifier) {
    val uriHandler = LocalUriHandler.current
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val size = with(density) { windowInfo.containerSize.toSize().toDpSize() }
    val navigator = rememberMainNavigator()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = KnightsTheme.colorScheme.background,
    ) {
        if (size.width > WideWidth) {
            Row(modifier = Modifier.fillMaxSize()) {
                ProjectDescription(
                    onGithubClick = {
                        uriHandler.openUri(ProjectUrl)
                    },
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .padding(40.dp),
                )
                // 스마트폰 프레임
                DeviceFrame(
                    modifier = Modifier
                        .padding(40.dp)
                        .width(360.dp)
                        .heightIn(max = 780.dp)
                        .fillMaxHeight(),
                    content = {
                        MainContent(navigator = navigator)
                    },
                )
            }
        } else {
            MainContent(navigator = navigator)
        }
    }
}

private val WideWidth = 900.dp
private const val ProjectUrl = "https://github.com/droidknights/DroidKnightsApp/tree/2025/compose-multiplatform"
