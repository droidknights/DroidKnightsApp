package com.droidknights.app.feature.setting

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
        }
    }
}
