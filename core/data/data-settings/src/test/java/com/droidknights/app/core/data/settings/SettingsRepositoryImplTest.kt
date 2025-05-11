package com.droidknights.app.core.data.settings

import app.cash.turbine.test
import com.droidknights.app.core.datastore.datasource.SettingsPreferencesDataSource
import com.droidknights.app.core.datastore.model.SettingsData
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
    fun `test flowIsDarkTheme`() = runTest {
        whenever(settingsPreferencesDataSource.settingsData).thenReturn(flowOf(SettingsData(isDarkTheme = true)))

        repository.flowIsDarkTheme().test {
            val result = awaitItem()

            assertTrue(result)

            verify(settingsPreferencesDataSource).settingsData

            cancelAndIgnoreRemainingEvents()
        }
    }
}
