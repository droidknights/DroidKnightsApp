package com.droidknights.app.core.data.settings

import app.cash.turbine.test
import com.droidknights.app.core.datastore.settings.api.SettingsPreferencesDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SettingsRepositoryImplTest {

    private val settingsPreferencesDataSource = mock<SettingsPreferencesDataSource>()

    private val repository = SettingsRepositoryImpl(
        preferencesDataSource = settingsPreferencesDataSource,
    )

    @Test
    fun `다크테마 여부를 Flow로 반환한다`() = runTest {
        whenever(settingsPreferencesDataSource.isDarkThemeFlow).thenReturn(flowOf(true))

        repository.flowIsDarkTheme().test {
            val result = awaitItem()

            assertTrue(result)

            verify(settingsPreferencesDataSource).isDarkThemeFlow

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `다크테마 여부를 업데이트할 수 있다`() = runTest {
        val isDarkTheme = false

        repository.updateIsDarkTheme(isDarkTheme)

        verify(settingsPreferencesDataSource).updateIsDarkTheme(isDarkTheme)
    }
}
