package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class GetBookmarkedSessionIdsUseCaseImplTest {

    private val sessionRepository = mock<SessionRepository>()

    private val useCase = GetBookmarkedSessionIdsUseCaseImpl(
        sessionRepository = sessionRepository
    )

    @Test
    fun `북마크된 세션들의 ID를 가져올 수 있다`() {
        val bookmarkedSessionIds = flowOf(
            setOf("1", "2", "3", "4"),
        )

        whenever(sessionRepository.getBookmarkedSessionIds()).thenReturn(bookmarkedSessionIds)

        Assertions.assertEquals(bookmarkedSessionIds, useCase.invoke())

        verify(sessionRepository).getBookmarkedSessionIds()
    }
}
