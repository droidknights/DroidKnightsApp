package com.droidknights.app2023.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavController
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavControllerInfo
import com.droidknights.app2023.feature.contributor.api.ContributorNavController
import com.droidknights.app2023.feature.home.api.HomeNavController
import com.droidknights.app2023.feature.home.api.HomeNavControllerInfo
import com.droidknights.app2023.feature.nav.DroidKnightsTab
import com.droidknights.app2023.feature.session.api.SessionDetailNavController
import com.droidknights.app2023.feature.session.api.SessionDetailNavControllerInfo
import com.droidknights.app2023.feature.session.api.SessionNavController
import com.droidknights.app2023.feature.setting.api.SettingNavController
import com.droidknights.app2023.feature.setting.api.SettingNavControllerInfo
import javax.inject.Inject

internal class MainNavigator(
    val navController: NavHostController,
    private val homeNavController: HomeNavController,
    private val bookmarkNavController: BookmarkNavController,
    private val contributorNavController: ContributorNavController,
    private val sessionNavController: SessionNavController,
    private val sessionDetailNavController: SessionDetailNavController,
    private val settingNavController: SettingNavController,
    private val mainTabs: MainTabs,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = mainTabs.startDestination()

    val currentTab: DroidKnightsTab?
        @Composable get() = currentDestination
            ?.route
            ?.let(mainTabs::find)

    fun navigate(tab: DroidKnightsTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab.route) {
            settingNavController.route() -> settingNavController.navigate(
                navController,
                SettingNavControllerInfo(navOptions)
            )

            homeNavController.route() -> homeNavController.navigate(
                navController,
                HomeNavControllerInfo(navOptions)
            )

            bookmarkNavController.route() -> bookmarkNavController.navigate(
                navController,
                BookmarkNavControllerInfo(navOptions)
            )
        }
    }

    fun navigateContributor() {
        contributorNavController.navigate(navController, Unit)
    }

    fun navigateSession() {
        sessionNavController.navigate(navController, Unit)
    }

    fun navigateSessionDetail(sessionId: String) {
        sessionDetailNavController.navigate(
            navController,
            SessionDetailNavControllerInfo(sessionId)
        )
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!homeNavController.isHomeRoute(navController.currentDestination?.route)) {
            popBackStack()
        }
    }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in mainTabs
    }

    class Factory @Inject constructor(
        private val homeNavController: HomeNavController,
        private val bookmarkNavController: BookmarkNavController,
        private val contributorNavController: ContributorNavController,
        private val sessionNavController: SessionNavController,
        private val sessionDetailNavController: SessionDetailNavController,
        private val settingNavController: SettingNavController,
        private val mainTabs: MainTabs,
        ) {
        fun create(navController: NavHostController): MainNavigator {
            return MainNavigator(
                navController,
                homeNavController,
                bookmarkNavController,
                contributorNavController,
                sessionNavController,
                sessionDetailNavController,
                settingNavController,
                mainTabs
            )
        }
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
    mainNavigatorFactory: MainNavigator.Factory
): MainNavigator = remember(navController) {
    mainNavigatorFactory.create(navController = navController)
}
