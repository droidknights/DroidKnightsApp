package com.droidknights.app.feature.session.list

import app.cash.turbine.test
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetSessionsUseCase
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.router.api.Navigator
import com.droidknights.app.core.testing.rule.MainDispatcherRule
import com.droidknights.app.feature.session.list.model.SessionUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class SessionListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getSessionsUseCase: GetSessionsUseCase = mockk()
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase = mockk()

    private val navigator = mockk<Navigator>()
    private lateinit var viewModel: SessionListViewModel

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

    @Test
    fun `세션 데이터를 확인할 수 있다`() = runTest {
        // given
        coEvery { getSessionsUseCase() } returns flowOf(listOf(fakeSession))
        coEvery { getBookmarkedSessionIdsUseCase() } returns flowOf(emptySet())
        viewModel = SessionListViewModel(
            getSessionsUseCase,
            getBookmarkedSessionIdsUseCase,
            navigator,
        )

        // when & then
        viewModel.uiState.test {
            val actual = (awaitItem() as? SessionUiState.Sessions)?.sessions?.first()
            assertEquals(fakeSession, actual)
        }
    }
}
