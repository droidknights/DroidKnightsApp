package com.droidknights.app2023.feature.session

import app.cash.turbine.test
import com.droidknights.app2023.core.domain.usecase.BookmarkSessionUseCase
import com.droidknights.app2023.core.domain.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app2023.core.domain.usecase.GetSessionUseCase
import com.droidknights.app2023.core.model.Level
import com.droidknights.app2023.core.model.Room
import com.droidknights.app2023.core.model.Session
import com.droidknights.app2023.core.testing.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs
import kotlin.test.assertTrue

class SessionDetailViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getSessionUseCase: GetSessionUseCase = mockk()
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase = mockk()
    private val bookmarkSessionUseCase: BookmarkSessionUseCase = mockk()
    private lateinit var viewModel: SessionDetailViewModel

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

    @Before
    fun setup() {
        coEvery { getBookmarkedSessionIdsUseCase() } returns flowOf(emptySet())
    }

    @Test
    fun `세션 데이터를 확인할 수 있다`() = runTest {
        // given
        val sessionId = "1"
        coEvery { getSessionUseCase(sessionId) } returns fakeSession
        viewModel = SessionDetailViewModel(
            getSessionUseCase,
            getBookmarkedSessionIdsUseCase,
            bookmarkSessionUseCase
        )

        // when
        viewModel.fetchSession(sessionId)

        // then
        viewModel.sessionUiState.test {
            val actual = awaitItem()
            assertIs<SessionDetailUiState.Success>(actual)
        }
    }

    @Test
    fun `세션의 북마크 여부를 확인할 수 있다`() = runTest {
        // given
        val sessionId = "1"
        coEvery { getSessionUseCase(sessionId) } returns fakeSession
        coEvery { getBookmarkedSessionIdsUseCase() } returns flowOf(setOf(sessionId))
        viewModel = SessionDetailViewModel(
            getSessionUseCase,
            getBookmarkedSessionIdsUseCase,
            bookmarkSessionUseCase
        )

        // when
        viewModel.fetchSession(sessionId)

        // then
        viewModel.sessionUiState.test {
            val actual = awaitItem() as SessionDetailUiState.Success
            assertTrue(actual.bookmarked)
        }
    }

    @Test
    fun `세션의 북마크 여부를 변경할 수 있다`() = runTest {
        // given
        val sessionId = "1"
        coEvery { getSessionUseCase(sessionId) } returns fakeSession

        val flow = MutableStateFlow(emptySet<String>())
        coEvery { getBookmarkedSessionIdsUseCase() } returns flow
        coEvery { bookmarkSessionUseCase(sessionId, true) } answers {
            flow.update { it + sessionId }
        }

        viewModel = SessionDetailViewModel(
            getSessionUseCase,
            getBookmarkedSessionIdsUseCase,
            bookmarkSessionUseCase
        )
        viewModel.fetchSession(sessionId)

        // when
        viewModel.toggleBookmark()

        // then
        viewModel.sessionUiState.test {
            val actual = awaitItem() as SessionDetailUiState.Success
            assertTrue(actual.bookmarked)
        }
    }
}
