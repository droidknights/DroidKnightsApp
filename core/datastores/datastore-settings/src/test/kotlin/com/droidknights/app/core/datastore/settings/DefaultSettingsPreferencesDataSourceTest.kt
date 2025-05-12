package com.droidknights.app.core.datastore.settings

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import io.kotest.core.spec.style.StringSpec
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.rules.TemporaryFolder
import kotlin.test.assertFalse

internal class DefaultSettingsPreferencesDataSourceTest : StringSpec() {

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var tempFolder: TemporaryFolder
    private lateinit var dataSource: DefaultSettingsPreferencesDataSource

    init {
        beforeSpec {
            testDispatcher = StandardTestDispatcher()
            tempFolder = TemporaryFolder.builder().assureDeletion().build()
            dataSource = DefaultSettingsPreferencesDataSource(
                PreferenceDataStoreFactory.create(
                    scope = CoroutineScope(testDispatcher),
                    produceFile = { tempFolder.newFile("SETTINGS_PREFERENCES_TEST") }
                )
            )
        }

        afterSpec {
            tempFolder.delete()
        }

        "isDarkTheme 초기상태 테스트".config(true) {
            CoroutineScope(testDispatcher).launch {
                // Given - 초기상태

                // When - dataSource 의 초기 isDarkTheme 값 조회
                val isDarkTheme = dataSource.isDarkThemeFlow.first()

                // Then - isDarkTheme 값이 false 이어야 한다
                assertFalse(isDarkTheme)
            }
        }

        "isDarkTheme 저장 및 조회 테스트" {
            CoroutineScope(testDispatcher).launch {
                // Given - isDarkTheme is true
                dataSource.updateIsDarkTheme(true)

                // When - isDarkTheme 을 true 로 업데이트 후 isDarkTheme 값 조회
                val isDarkTheme = dataSource.isDarkThemeFlow.first()

                // Then - isDarkTheme 값이 true 이어야 한다
                assertTrue(isDarkTheme)
            }
        }
    }
}
