package com.droidknights.app.feature.main

import com.droidknights.app.core.data.settings.api.SettingsRepository
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val settingsRepository = mockk<SettingsRepository>()
    private lateinit var viewModel: MainViewModel

    @Test
    fun `현재 다크테마 여부를 가져올 수 있다`() = runTest {
        // given
        every { settingsRepository.flowIsDarkTheme() } returns flowOf(false)
        viewModel = MainViewModel(settingsRepository)

        // when & then
        assertFalse(viewModel.isDarkTheme.single())
        verify(exactly = 1) { settingsRepository.flowIsDarkTheme() }
    }

    @Test
    fun `다크테마 여부를 업데이트 할 수 있다`() = runTest {
        // given
        every { settingsRepository.flowIsDarkTheme() } returns flowOf(false)
        coEvery { settingsRepository.updateIsDarkTheme(true) } just Runs
        viewModel = MainViewModel(settingsRepository)

        // when
        viewModel.updateIsDarkTheme(true)

        // then
        coVerify(exactly = 1) { settingsRepository.updateIsDarkTheme(true) }
    }
}
