package com.droidknights.app.feature.main.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.droidknights.app.feature.bookmark.navigation.bookmarkNavGraph
import com.droidknights.app.feature.contributor.navigation.contributorNavGraph
import com.droidknights.app.feature.home.navigation.homeNavGraph
import com.droidknights.app.feature.license.navigation.licenseNavGraph
import com.droidknights.app.feature.main.MainNavigator
import com.droidknights.app.feature.session.navigation.sessionNavGraph
import com.droidknights.app.feature.setting.navigation.settingNavGraph

@Composable
internal fun MainNavHost(
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navigator.navController,
        startDestination = navigator.startDestination,
    ) {
        settingNavGraph(
            onLicenseClick = {navigator.navigateLicense()}
        )
        homeNavGraph(
            onSessionClick = { navigator.navigateSession() },
            onContributorClick = { navigator.navigateContributor() },
        )
        bookmarkNavGraph()
        contributorNavGraph(
            onBackClick = navigator::popBackStackIfNotHome,
        )
        sessionNavGraph(
            onBackClick = navigator::popBackStackIfNotHome,
            onSessionClick = { sessionId ->
                navigator.navigateSessionDetail(sessionId)
            },
        )
        licenseNavGraph()

    }
}
