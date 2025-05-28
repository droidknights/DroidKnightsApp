package com.droidknights.app.core.datastore.session

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

internal class DefaultSessionPreferencesDataSourceTest {
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
    private lateinit var tempFile: File
    private lateinit var dataSource: DefaultSessionPreferencesDataSource

    @BeforeEach
    fun setUp() {
        val tempFolder = createTempDirectory().toFile()
        tempFile = File(tempFolder, "session_prefs.preferences_pb")
        dataSource = DefaultSessionPreferencesDataSource(
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
    fun `초기 bookmarkedSession 값은 emptySet이어야 한다`() = runTest(testDispatcher) {
        // given & when
        dataSource.bookmarkedSession.test {
            val initial: Set<String> = awaitItem()

            // then
            Assertions.assertEquals(emptySet<String>(), initial)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `bookmarkedSession 업데이트 시 Flow에서 새로운 값이 emit되어야 한다`() = runTest(testDispatcher) {
        // given
        dataSource.bookmarkedSession.test {
            val initial: Set<String> = awaitItem()

            // when
            dataSource.updateBookmarkedSession(setOf("1", "2"))
            val updated: Set<String> = awaitItem()

            // then
            Assertions.assertEquals(emptySet<String>(), initial)
            Assertions.assertEquals(setOf("1", "2"), updated)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
