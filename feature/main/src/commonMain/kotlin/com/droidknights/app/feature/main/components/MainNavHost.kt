package com.droidknights.app.feature.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidknights.app.core.designsystem.components.Button
import com.droidknights.app.feature.bookmark.navigation.bookmarkNavGraph
import com.droidknights.app.feature.bookmark.navigation.navigateBookmark
import com.droidknights.app.feature.contributor.navigation.contributorNavGraph
import com.droidknights.app.feature.contributor.navigation.navigateContributor
import com.droidknights.app.feature.home.navigation.homeNavGraph
import com.droidknights.app.feature.home.navigation.navigateHome
import com.droidknights.app.feature.navigation.navigateSetting
import com.droidknights.app.feature.navigation.settingNavGraph
import com.droidknights.app.feature.session.navigation.navigateSession
import com.droidknights.app.feature.session.navigation.navigateSessionDetail
import com.droidknights.app.feature.session.navigation.sessionNavGraph

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = "develop-2025",
    ) {
        // TODO 각 feature 개발 후 제거 혹은 개발자 도구로 이동
        composable("develop-2025") {
            DevelopScreen(navController)
        }
        homeNavGraph(navController::popBackStack)
        settingNavGraph(navController::popBackStack)
        bookmarkNavGraph(navController::popBackStack)
        contributorNavGraph(navController::popBackStack)
        sessionNavGraph(navController::popBackStack)
    }
}

// TODO 각 feature 개발 후 제거 혹은 개발자 도구로 이동
@Composable
private fun DevelopScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button("Bookmark") {
            navController.navigateBookmark()
        }
        Button("Contributor") {
            navController.navigateContributor()
        }
        Button("Home") {
            navController.navigateHome()
        }
        Button("Session List") {
            navController.navigateSession()
        }
        Button("Session Detail") {
            navController.navigateSessionDetail("sample:123")
        }
        Button("Setting") {
            navController.navigateSetting()
        }
    }
}
