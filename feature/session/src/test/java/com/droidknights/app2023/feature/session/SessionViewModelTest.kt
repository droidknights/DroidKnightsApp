package com.droidknights.app2023.feature.session

import app.cash.turbine.test
import com.droidknights.app2023.core.domain.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app2023.core.domain.usecase.GetSessionsUseCase
import com.droidknights.app2023.core.model.Level
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.testing.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class SessionViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getSessionsUseCase: GetSessionsUseCase = mockk()
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase = mockk()
    private lateinit var viewModel: SessionViewModel

    private val fakeSession = Session(
        id = "1",
        title = "title",
        content = "content",
        speakers = emptyList(),
        level = Level.BASIC,
        tags = emptyList(),
        room = Room.TRACK1,
        startTime = LocalDateTime(2023, 9, 12, 13, 0, 0),
        endTime = LocalDateTime(2023, 9, 12, 13, 30, 0),
        isBookmarked = false
    )

    @Test
    fun `세션 데이터를 확인할 수 있다`() = runTest {
        // given
        coEvery { getSessionsUseCase() } returns listOf(fakeSession)
        coEvery { getBookmarkedSessionIdsUseCase() } returns flowOf(emptySet())
        viewModel = SessionViewModel(getSessionsUseCase, getBookmarkedSessionIdsUseCase)

        // when & then
        viewModel.uiState.test {
            val actual = (awaitItem() as? SessionUiState.Sessions)?.sessions?.first()
            assertEquals(fakeSession, actual)
        }
    }
}
