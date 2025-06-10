package com.droidknights.app.feature.setting

<<<<<<< HEAD
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
=======
import app.cash.turbine.test
import com.droidknights.app.core.action.api.FlowActionStream
import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

internal class SettingViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val settingsRepository = mockk<SettingsRepository>()
    private val flowActionStream = mockk<FlowActionStream>()
    private lateinit var viewModel: SettingViewModel

    @Test
    fun `다크테마 여부를 업데이트 할 수 있다`() = runTest {
        // given
        every { flowActionStream.flowAction() } returns flowOf(SettingAction.ChangeDarkTheme(true))
        coEvery { settingsRepository.updateIsDarkTheme(true) } just Runs

        viewModel = SettingViewModel(
            settingsRepository = settingsRepository,
            flowActionStream = flowActionStream,
        )

        viewModel.flowAction.test {
            // then
            coVerify(exactly = 1) { settingsRepository.updateIsDarkTheme(true) }

            cancelAndIgnoreRemainingEvents()
>>>>>>> 070bad67 ((FEAT)[#468] 설정 - 액션 시스템 적용)
        }
    }
}
