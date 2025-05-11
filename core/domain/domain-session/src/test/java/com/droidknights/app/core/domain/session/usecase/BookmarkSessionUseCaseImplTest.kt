package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class BookmarkSessionUseCaseImplTest {

    private val sessionRepository = mock<SessionRepository>()

    private val domain = BookmarkSessionUseCaseImpl(
        sessionRepository = sessionRepository,
    )

    @Test
    fun `test invoke`() = runTest {
        val sessionId = "id"
        val bookmark = true
        domain.invoke(sessionId, bookmark)

        verify(sessionRepository).bookmarkSession(sessionId, bookmark)
    }
}
