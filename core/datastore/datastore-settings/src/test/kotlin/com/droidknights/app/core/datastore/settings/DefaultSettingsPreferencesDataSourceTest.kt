package com.droidknights.app.core.datastore.settings

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import app.cash.turbine.test
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.io.path.createTempDirectory

internal class DefaultSettingsPreferencesDataSourceTest {

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
    private lateinit var tempFile: File
    private lateinit var dataSource: DefaultSettingsPreferencesDataSource

    @BeforeEach
    fun setUp() {
        val tempFolder = createTempDirectory().toFile()
        tempFile = File(tempFolder, "settings_prefs.preferences_pb")
        dataSource = DefaultSettingsPreferencesDataSource(
            PreferenceDataStoreFactory.create(
                scope = CoroutineScope(testDispatcher),
                produceFile = { tempFile }
            )
        )
    }

    @AfterEach
    fun tearDown() {
        tempFile.delete()
    }

    @Test
    fun `isDarkTheme의 초기 상태는 false이다`() = runTest(testDispatcher) {
        // given & when
        dataSource.isDarkThemeFlow.test {
            val initial: Boolean = awaitItem()

            // then
            Assertions.assertFalse(initial)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `true로 업데이트 하면 isDarkTheme는 true다`() = runTest(testDispatcher) {
        // given
        dataSource.isDarkThemeFlow.test {
            val initial: Boolean = awaitItem()

            // when
            dataSource.updateIsDarkTheme(true)
            val updated: Boolean = awaitItem()

            // then
            Assertions.assertFalse(initial)
            Assertions.assertTrue(updated)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
