package com.droidknights.app2023.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.droidknights.app2023.core.designsystem.theme.Neon01
import com.droidknights.app2023.core.designsystem.theme.surfaceDim
import com.droidknights.app2023.feature.bookmark.navigation.bookmarkNavGraph
import com.droidknights.app2023.feature.contributor.navigation.contributorNavGraph
import com.droidknights.app2023.feature.home.navigation.homeNavGraph
import com.droidknights.app2023.feature.session.navigation.sessionNavGraph
import com.droidknights.app2023.feature.setting.navigation.settingNavGraph
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    onChangeDarkTheme: (Boolean) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val coroutineScope = rememberCoroutineScope()
    val localContextResource = LocalContext.current.resources
    val onShowErrorSnackBar: (throwable: Throwable?) -> Unit = { throwable ->
        coroutineScope.launch {
            snackBarHostState.showSnackbar(
                when (throwable) {
                    is UnknownHostException -> localContextResource.getString(R.string.error_message_network)
                    else -> localContextResource.getString(R.string.error_message_unknown)
                }
            )
        }
    }

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceDim)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination,
                ) {
                    homeNavGraph(
                        padding = padding,
                        onSessionClick = { navigator.navigateSession() },
                        onContributorClick = { navigator.navigateContributor() },
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                    settingNavGraph(
                        padding = padding,
                        onChangeDarkTheme = onChangeDarkTheme
                    )

                    bookmarkNavGraph(
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )

                    contributorNavGraph(
                        onBackClick = navigator::popBackStackIfNotHome,
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )

                    sessionNavGraph(
                        onBackClick = navigator::popBackStackIfNotHome,
                        onSessionClick = { navigator.navigateSessionDetail(it.id) },
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.values().toList().toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    )
}

@Composable
private fun MainBottomBar(
    visible: Boolean,
    tabs: PersistentList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) }
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(start = 8.dp, end = 8.dp, bottom = 28.dp)
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(size = 28.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            tabs.forEach { tab ->
                MainBottomBarItem(
                    tab = tab,
                    selected = tab == currentTab,
                    onClick = { onTabSelected(tab) },
                )
            }
        }
    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(tab.iconResId),
            contentDescription = tab.contentDescription,
            tint = if (selected) {
                Neon01
            } else {
                MaterialTheme.colorScheme.outline
            },
            modifier = Modifier.size(34.dp),
        )
    }
}
