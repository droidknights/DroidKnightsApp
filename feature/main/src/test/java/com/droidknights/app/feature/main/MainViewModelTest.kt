package com.droidknights.app.feature.main

import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import com.droidknights.app.feature.session.api.RouteSessionDetail
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val settingsRepository = mockk<SettingsRepository>()
    private val navigator = mockk<Navigator>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        // given
        every { settingsRepository.flowIsDarkTheme() } returns flowOf(false)
        viewModel = MainViewModel(settingsRepository, navigator)
    }

    @Test
    fun `현재 다크테마 여부를 가져올 수 있다`() = runTest {
        // when & then
        assertFalse(viewModel.isDarkTheme.single())
        verify(exactly = 1) { settingsRepository.flowIsDarkTheme() }
    }

    @Test
    fun `다크테마 여부를 업데이트 할 수 있다`() = runTest {
        // given
        coEvery { settingsRepository.updateIsDarkTheme(true) } just Runs

        // when
        viewModel.updateIsDarkTheme(true)

        // then
        coVerify(exactly = 1) { settingsRepository.updateIsDarkTheme(true) }
    }

    @Test
    fun `navigate(RouteSessionDetail)가 호출될 때 navigator에게 위임한다`() = runTest {
        // given
        val fakeSessionId = "1"
        coEvery { navigator.navigate(RouteSessionDetail(fakeSessionId)) } just Runs

        // when
        viewModel.navigateSessionDetail(fakeSessionId)

        // then
        coVerify(exactly = 1) { navigator.navigate(RouteSessionDetail(fakeSessionId)) }
    }
}
