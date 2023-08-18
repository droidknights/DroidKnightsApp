package com.droidknights.app2023.core.datastore

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import io.kotest.core.spec.style.StringSpec
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.rules.TemporaryFolder

internal class SettingsPreferencesDataSourceTest : StringSpec() {

    init {
        val testDispatcher = StandardTestDispatcher()
        val tempFolder = TemporaryFolder.builder().assureDeletion().build()

        // Given - SettingsPreferencesDatasource 제공
        val dataSource = SettingsPreferencesDataSource(
            PreferenceDataStoreFactory.create(
                scope = CoroutineScope(testDispatcher),
                produceFile = { tempFolder.newFile("SETTINGS_PREFERENCES_TEST") }
            )
        )

        "DataStore 의 초기 상태 테스트" {
            CoroutineScope(testDispatcher).launch {
                // When - dataSource 의 초기 SettingsData 값 조회
                val settingsData = dataSource.settingsData.first()

                // Then - SettingsData.isDarkTheme 값이 false 이어야 한다
                assert(settingsData.isDarkTheme == false)
            }
        }

        "isDarkTheme 저장 및 조회 테스트" {
            CoroutineScope(testDispatcher).launch {
                // When - isDarkTheme 을 true 로 업데이트 후 SettingsData 값 조회
                dataSource.updateIsDarkTheme(true)
                val settingsData = dataSource.settingsData.first()

                // Then - SettingsData.isDarkTheme 값이 true 이어야 한다
                assert(settingsData.isDarkTheme == true)
            }
        }
    }
}
