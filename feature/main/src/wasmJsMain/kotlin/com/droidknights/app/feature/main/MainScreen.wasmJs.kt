package com.droidknights.app.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.main.components.DeviceFrame
import com.droidknights.app.feature.main.components.ProjectDescription
import droidknights.feature.main.generated.resources.Res
import droidknights.feature.main.generated.resources.ic_github
import droidknights.feature.main.generated.resources.web_logo
import org.jetbrains.compose.resources.painterResource

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
            Column(
                modifier = Modifier
                    .requiredWidthIn(max = 1280.dp)
                    .fillMaxSize(),
            ) {
                AppBar(
                    onGithubClick = {
                        uriHandler.openUri(ProjectUrl)
                    },
                )
                Row {
                    ProjectDescription(
                        onContributorClick = {
                            uriHandler.openUri(it.profileUrl)
                        },
                        onProjectBranchClick = {
                            uriHandler.openUri(it.url)
                        },
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight()
                            .padding(40.dp, 20.dp),
                    )

                    DeviceFrame(
                        modifier = Modifier
                            .padding(40.dp, 20.dp)
                            .width(360.dp)
                            .heightIn(max = 780.dp)
                            .fillMaxHeight(),
                        content = {
                            MainContent(navigator = navigator)
                        },
                    )
                }
            }
        } else {
            MainContent(navigator = navigator)
        }
    }
}

@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    onGithubClick: () -> Unit,
) {
    Box(modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .height(40.dp)
                .align(Alignment.CenterStart),
            painter = painterResource(Res.drawable.web_logo),
            contentDescription = null,
        )

        Icon(
            painter = painterResource(Res.drawable.ic_github),
            contentDescription = null,
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .clip(CircleShape)
                .size(40.dp)
                .clickable(onClick = onGithubClick)
                .align(Alignment.CenterEnd),
        )
    }
}

private val WideWidth = 1280.dp
private const val ProjectUrl = "https://github.com/droidknights/DroidKnightsApp/tree/2025/compose-multiplatform"
