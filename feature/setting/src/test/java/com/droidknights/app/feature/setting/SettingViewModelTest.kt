package com.droidknights.app.feature.setting

import com.droidknights.app.core.navigation.MainTabRoute.Bookmark
import com.droidknights.app.core.navigation.MainTabRoute.Home
import com.droidknights.app.core.router.api.Navigator
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SettingViewModelTest {
    private val navigator = mockk<Navigator>()
    private lateinit var viewModel: SettingViewModel

    @Before
    fun setUp() {
        // given
        viewModel = SettingViewModel(navigator)
    }

    @Test
    fun `navigate(Home)가 호출될 때 navigator에게 위임한다`() = runTest {
        // given
        coEvery {
            navigator.navigate(
                route = Home,
                saveState = Home.saveState,
                launchSingleTop = Home.launchSingleTop,
            )
        } just Runs

        // when
        viewModel.navigateHome()

        // then
        coVerify(exactly = 1) {
            navigator.navigate(
                route = Home,
                saveState = Home.saveState,
                launchSingleTop = Home.launchSingleTop,
            )
        }
    }

    @Test
    fun `navigate(Bookmark)가 호출될 때 navigator에게 위임한다`() = runTest {
        // given
        coEvery {
            navigator.navigate(
                route = Bookmark,
                saveState = Bookmark.saveState,
                launchSingleTop = Bookmark.launchSingleTop,
            )
        } just Runs

        // when
        viewModel.navigateBookmark()

        // then
        coVerify(exactly = 1) {
            navigator.navigate(
                route = Bookmark,
                saveState = Bookmark.saveState,
                launchSingleTop = Bookmark.launchSingleTop,
            )
        }
    }
}
