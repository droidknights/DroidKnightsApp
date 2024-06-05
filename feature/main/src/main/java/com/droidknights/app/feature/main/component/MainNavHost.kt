package com.droidknights.app.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.droidknights.app.feature.bookmark.navigation.bookmarkNavGraph
import com.droidknights.app.feature.contributor.navigation.contributorNavGraph
import com.droidknights.app.feature.home.navigation.homeNavGraph
import com.droidknights.app.feature.main.MainNavigator
import com.droidknights.app.feature.session.navigation.sessionNavGraph
import com.droidknights.app.feature.setting.navigation.settingNavGraph

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onChangeDarkTheme: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier
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
}
