package com.droidknights.app2023.feature.main

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.droidknights.app2023.core.navigation.HomeNavigation
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    
    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreen(MainNavigator(navController, FakeHomeNavigation()))
        }
    }
    
    @Test
    fun 시작_화면은_홈이다() {
        val actual = navController.currentBackStackEntry?.destination?.route
        assertEquals("home", actual)
    }
    
    @Test
    fun 설정_탭을_클릭하면_설정으로_이동한다() {
        // when
        composeTestRule
            .onNodeWithContentDescription("설정")
            .performClick()
        
        // then
        val actual = navController.currentBackStackEntry?.destination?.route
        assertEquals("setting", actual)
    }
}

private class FakeHomeNavigation : HomeNavigation {
    @Composable
    override fun Content() {
        // no-op
    }
}
