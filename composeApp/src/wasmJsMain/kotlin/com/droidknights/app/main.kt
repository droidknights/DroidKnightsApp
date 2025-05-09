@file:Suppress("Filename")

package com.droidknights.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.ComposeViewport
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val uriHandler = LocalUriHandler.current
        val density = LocalDensity.current
        val windowInfo = LocalWindowInfo.current
        val size = with(density) { windowInfo.containerSize.toSize().toDpSize() }

        KnightsTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = KnightsTheme.colorScheme.background,
            ) {
                if (size.width > WideWidth) {
                    ProjectDescription(
                        onGithubClick = {
                            uriHandler.openUri(ProjectUrl)
                        },
                        app = {
                            App()
                        },
                    )
                } else {
                    App()
                }
            }
        }
    }
}

private val WideWidth = 900.dp
private const val ProjectUrl = "https://github.com/droidknights/DroidKnightsApp/tree/2025/compose-multiplatform"
