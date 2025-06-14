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
    fun `세션을 북마크하거나 해제할 수 있다`() = runTest {
        val sessionId = "id"
        val bookmark = true
        domain.invoke(sessionId, bookmark)

        verify(sessionRepository).bookmarkSession(sessionId, bookmark)
    }
}
