package com.droidknights.app.feature.session.detail

import app.cash.turbine.test
import com.droidknights.app.core.domain.session.usecase.api.BookmarkSessionUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetSessionUseCase
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import com.droidknights.app.feature.session.detail.model.SessionDetailUiState
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
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
    private val navigator = mockk<Navigator>()
    private lateinit var viewModel: SessionDetailViewModel

    private val fakeSession = Session(
        id = "1",
        title = "title",
        content = "content",
        speakers = emptyList(),
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
            bookmarkSessionUseCase,
            navigator,
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
            bookmarkSessionUseCase,
            navigator,
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
            bookmarkSessionUseCase,
            navigator,
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

    @Test
    fun `navigateBack이 호출될 때 navigator에게 위임한다`() = runTest {
        // suspend 함수 호출에 대한 stub
        coEvery { navigator.navigateBack() } just Runs
        viewModel = SessionDetailViewModel(
            getSessionUseCase,
            getBookmarkedSessionIdsUseCase,
            bookmarkSessionUseCase,
            navigator,
        )

        // when
        viewModel.navigateBack()

        // then
        coVerify(exactly = 1) { navigator.navigateBack() }
    }
}
