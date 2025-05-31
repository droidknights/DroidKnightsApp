package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

internal class GetSessionUseCaseImplTest {

    private val sessionRepository = mock<SessionRepository>()
    private val useCase = GetSessionUseCaseImpl(
        sessionRepository = sessionRepository,
    )

    @Test
    fun `세션의 ID로 세션을 가져올 수 있다`() = runTest {
        val sessionId = "1"
        val session = Session(
            id = sessionId,
            title = "키노트",
            content = "",
            speakers = emptyList(),
            tags = emptyList(),
            room = Room.ETC,
            startTime = LocalDateTime(2025, 6, 17, 10, 40),
            endTime = LocalDateTime(2025, 6, 17, 11, 0),
            isBookmarked = false
        )

        whenever(sessionRepository.getSession(sessionId)).thenReturn(session)

        Assertions.assertEquals(session, useCase.invoke(sessionId))

        verify(sessionRepository).getSession(sessionId)
    }
}
