package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.model.session.Room
import com.droidknights.app.core.model.session.Session
import com.droidknights.app.core.model.session.Speaker
import com.droidknights.app.core.model.session.Tag
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class GetSessionsUseCaseImplTest {

    private val sessionRepository = mock<SessionRepository>()

    private val useCase = GetSessionsUseCaseImpl(
        sessionRepository = sessionRepository,
    )

    @Test
    fun `모든 세션들을 가져올 수 있다`() = runTest {
        val sessions = listOf(
            Session(
                id = "3",
                title = "Item3 Title",
                content = "Item3 Content",
                speakers = listOf(Speaker(name = "철수", introduction = "", imageUrl = "")),
                tags = listOf(Tag(name = "Architecture")),
                room = Room.TRACK1,
                startTime = LocalDateTime(2023, 10, 5, 11, 0),
                endTime = LocalDateTime(2023, 10, 5, 11, 50),
                isBookmarked = false
            ),
            Session(
                id = "1",
                title = "Item1 Title",
                content = "Item1 Content",
                speakers = listOf(Speaker(name = "영희", introduction = "", imageUrl = "")),
                tags = listOf(Tag(name = "Architecture")),
                room = Room.TRACK1,
                startTime = LocalDateTime(2023, 10, 5, 9, 0),
                endTime = LocalDateTime(2023, 10, 5, 9, 50),
                isBookmarked = false
            ),
            Session(
                id = "2",
                title = "Item2 Title",
                content = "Item2 Content",
                speakers = listOf(Speaker(name = "민수", introduction = "", imageUrl = "")),
                tags = listOf(Tag(name = "Architecture")),
                room = Room.TRACK1,
                startTime = LocalDateTime(2023, 10, 5, 10, 0),
                endTime = LocalDateTime(2023, 10, 5, 10, 50),
                isBookmarked = false
            )
        )

        whenever(sessionRepository.getSessions()).thenReturn(sessions)

        Assertions.assertEquals(sessions, useCase.invoke().single())

        verify(sessionRepository).getSessions()
    }
}
