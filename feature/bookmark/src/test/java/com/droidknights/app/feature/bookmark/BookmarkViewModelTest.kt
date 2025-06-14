package com.droidknights.app.feature.bookmark

import app.cash.turbine.test
import com.droidknights.app.core.domain.session.usecase.api.DeleteBookmarkedSessionUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionsUseCase
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.core.model.session.Tag
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import com.droidknights.app.feature.bookmark.model.BookmarkItemUiState
import com.droidknights.app.feature.bookmark.model.BookmarkUiState
import com.droidknights.app.feature.session.api.RouteSession
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BookmarkViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getBookmarkedSessionsUseCase: GetBookmarkedSessionsUseCase = mockk()
    private val deleteBookmarkedSessionUseCase: DeleteBookmarkedSessionUseCase = mockk()
    private val navigator = mockk<Navigator>()
    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp() {
        coEvery { getBookmarkedSessionsUseCase() } returns flowOf(mockBookmarkedSessions)
    }

    @Test
    fun `북마크한 세션들을 가져올 수 있다`() = runTest {
        // given & when
        viewModel = BookmarkViewModel(
            getBookmarkedSessionsUseCase,
            deleteBookmarkedSessionUseCase,
            navigator
        )

        // then
        viewModel.bookmarkUiState.test {
            val actual: BookmarkUiState = awaitItem()
            assertEquals(
                expected = BookmarkUiState.Success(
                    bookmarks = mockBookmarkedSessions
                        .mapIndexed { index, session ->
                            BookmarkItemUiState(
                                index = index,
                                session = session,
                            )
                        }.toPersistentList()
                ),
                actual = actual,
            )
        }
        coVerify { getBookmarkedSessionsUseCase() }
    }

    @Test
    fun `에딧모드를 토글할 수 있다`() = runTest {
        // given
        viewModel = BookmarkViewModel(
            getBookmarkedSessionsUseCase,
            deleteBookmarkedSessionUseCase,
            navigator
        )

        viewModel.bookmarkUiState.test {
            val initialIsEditMode = (awaitItem() as BookmarkUiState.Success).isEditMode

            // when
            viewModel.toggleEditMode()
            val actual = (awaitItem() as BookmarkUiState.Success).isEditMode

            // then
            assertFalse(initialIsEditMode)
            assertTrue(actual)
        }
    }

    @Test
    fun `세션을 선택할 수 있다`() = runTest {
        // given
        viewModel = BookmarkViewModel(
            getBookmarkedSessionsUseCase,
            deleteBookmarkedSessionUseCase,
            navigator
        )

        // when
        viewModel.selectSession(mockSession1)

        // then
        viewModel.bookmarkUiState.test {
            val actual = awaitItem() as BookmarkUiState.Success
            assertEquals(
                expected = persistentSetOf("1"),
                actual = actual.selectedSessionIds,
            )
        }
    }

    @Test
    fun `이미 선택된 세션을 선택하면 선택이 해제된다`() = runTest {
        // given
        viewModel = BookmarkViewModel(
            getBookmarkedSessionsUseCase,
            deleteBookmarkedSessionUseCase,
            navigator
        )
        viewModel.selectSession(mockSession1)

        // when
        viewModel.selectSession(mockSession1)

        // then
        viewModel.bookmarkUiState.test {
            val actual = (awaitItem() as BookmarkUiState.Success).selectedSessionIds
            assertEquals(
                expected = persistentSetOf(),
                actual = actual,
            )
        }
    }

    @Test
    fun `선택된 세션들을 북마크에서 삭제할 수 있다`() = runTest {
        // given
        coEvery { deleteBookmarkedSessionUseCase(persistentSetOf("1")) } just Runs
        viewModel = BookmarkViewModel(
            getBookmarkedSessionsUseCase,
            deleteBookmarkedSessionUseCase,
            navigator
        )
        viewModel.selectSession(mockSession1)

        // when
        viewModel.deleteSessions()

        // then
        viewModel.bookmarkUiState.test {
            val actual = (awaitItem() as BookmarkUiState.Success).selectedSessionIds
            assertEquals(persistentSetOf(), actual)
            coVerify { deleteBookmarkedSessionUseCase(persistentSetOf("1")) }
        }
    }

    @Test
    fun `북마크 목록의 아이템을 클릭하면 세션 목록 화면으로 이동한다`() = runTest {
        // given
        coEvery { navigator.navigateBack() } just Runs
        coEvery { navigator.navigate(RouteSession(sessionId = mockSession2.id)) } just Runs
        viewModel = BookmarkViewModel(
            getBookmarkedSessionsUseCase,
            deleteBookmarkedSessionUseCase,
            navigator
        )

        // when
        viewModel.redirectToSessionScreen(mockSession2)

        // then
        coVerify(exactly = 1) { navigator.navigate(RouteSession(sessionId = mockSession2.id)) }
    }

    companion object {
        private val mockSession1 = Session(
            id = "1",
            title = "Item1 Title",
            content = "Item1 Content",
            speakers = listOf(Speaker(name = "영희", introduction = "", imageUrl = "")),
            tags = listOf(Tag(name = "Architecture")),
            room = Room.TRACK1,
            startTime = LocalDateTime(2023, 10, 5, 9, 0),
            endTime = LocalDateTime(2023, 10, 5, 9, 50),
            isBookmarked = true
        )
        private val mockSession2 = Session(
            id = "2",
            title = "Item2 Title",
            content = "Item2 Content",
            speakers = listOf(Speaker(name = "철수", introduction = "", imageUrl = "")),
            tags = listOf(Tag(name = "Architecture")),
            room = Room.TRACK1,
            startTime = LocalDateTime(2023, 10, 5, 11, 0),
            endTime = LocalDateTime(2023, 10, 5, 11, 50),
            isBookmarked = true
        )
        private val mockBookmarkedSessions: List<Session> = listOf(
            mockSession1,
            mockSession2,
        )
    }
}
