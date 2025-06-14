package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class DeleteBookmarkedSessionUseCaseImplTest {

    private val sessionRepository = mock<SessionRepository>()
    private val useCase = DeleteBookmarkedSessionUseCaseImpl(sessionRepository)

    @Test
    fun `북마크된 세션들을 삭제할 수 있다`() = runTest {
        val sessionIds = setOf("1", "2", "3")

        useCase.invoke(sessionIds)

        verify(sessionRepository).deleteBookmarkedSessions(sessionIds)
    }
}
