package com.droidknights.app.feature.main

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.droidknights.app.core.navigation.MainTabRoute
import com.droidknights.app.core.uitesthiltmanifest.HiltComponentActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreen(
                rememberMainNavigator(navController = navController),
            )
        }
    }

    @Test
    fun 시작_화면은_홈이다() {
        val actual = navController.currentDestination?.hasRoute<MainTabRoute.Home>()
        assertEquals(true, actual)
    }

    @Test
    fun 설정_탭을_클릭하면_설정으로_이동한다() {
        // when
        composeTestRule
            .onNodeWithContentDescription("설정")
            .performClick()

        // then
        val actual = navController.currentDestination?.hasRoute<MainTabRoute.Setting>()
        assertEquals(true, actual)
    }
}
