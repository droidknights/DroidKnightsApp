package com.droidknights.app.feature.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.droidknights.app.core.designsystem.components.Button
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.router.LaunchedRouter
import com.droidknights.app.feature.main.components.AppBar
import com.droidknights.app.feature.main.components.DeviceFrame
import com.droidknights.app.feature.main.components.MultiPlatformButton
import com.droidknights.app.feature.main.components.ProjectDescription

@Composable
actual fun MainScreen(modifier: Modifier) {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val size = with(density) { windowInfo.containerSize.toSize().toDpSize() }
    val navigator = rememberMainNavigator()

    LaunchedRouter(
        navHostController = navigator.navController,
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.Black,
        contentColor = Color.White,
    ) {
        if (size.width > WideWidth) {
            MainDesktopScreen(
                navigator = navigator,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 80.dp),
            )
        } else {
            MainMobileScreen(navigator = navigator)
        }
    }
}

@Composable
private fun MainDesktopScreen(
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier,
    ) {
        AppBar(
            isMobile = false,
            modifier = Modifier.padding(top = 40.dp, bottom = 20.dp),
            onProjectBranchClick = {
                uriHandler.openUri(it.url)
            },
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(144.dp),
        ) {
            ProjectDescription(
                onContributorClick = {
                    uriHandler.openUri(it.profileUrl)
                },
                isMobile = false,
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .weight(1F)
                    .fillMaxHeight()
                    .padding(start = 58.dp),
            )
            DeviceFrame(
                modifier = Modifier
                    .padding(vertical = 36.dp)
                    .width(360.dp)
                    .heightIn(min = 400.dp, max = 800.dp)
                    .fillMaxHeight(),
                content = {
                    MainContent(navigator = navigator)
                },
            )
        }
    }
}

@Composable
private fun MainMobileScreen(
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    var mode by remember { mutableStateOf(MainMobileMode.Description) }

    var showExitGuidePopup by remember { mutableStateOf(false) }

    if (showExitGuidePopup) {
        Dialog(
            onDismissRequest = {
                showExitGuidePopup = false
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            ),
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        text = "데모를 종료하려면\n페이지를 새로고침 해주세요.",
                        style = KnightsTheme.typography.titleMediumR,
                        textAlign = TextAlign.Center,
                    )
                    Button(
                        text = "확인",
                        onClick = {
                            mode = MainMobileMode.Demo
                            showExitGuidePopup = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }

    AnimatedContent(
        mode,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
    ) {
        when (it) {
            MainMobileMode.Description -> {
                Column(
                    modifier = modifier,
                ) {
                    AppBar(
                        isMobile = true,
                        modifier = Modifier.padding(
                            top = 56.dp,
                            bottom = 8.dp,
                            start = 24.dp,
                            end = 24.dp,
                        ),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ProjectDescription(
                        onContributorClick = {
                            uriHandler.openUri(it.profileUrl)
                        },
                        isMobile = true,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .weight(1F)
                            .fillMaxHeight()
                            .padding(horizontal = 24.dp),
                        onBranchButtonsClick = { uriHandler.openUri(it.url) },
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        val infiniteTransition = rememberInfiniteTransition()

                        val offsetY by infiniteTransition.animateFloat(
                            initialValue = 0F,
                            targetValue = -4F,
                            animationSpec = infiniteRepeatable(
                                animation = tween(500),
                                repeatMode = RepeatMode.Reverse,
                            ),
                        )
                        MultiPlatformButton(
                            text = "멀티플랫폼 앱 체험하기",
                            onClick = {
                                showExitGuidePopup = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(x = 0.dp, y = offsetY.dp),
                        )
                    }
                }
            }

            MainMobileMode.Demo -> {
                MainContent(navigator = navigator)
            }
        }
    }
}

private enum class MainMobileMode {
    Description, Demo
}

private val WideWidth = 1280.dp
