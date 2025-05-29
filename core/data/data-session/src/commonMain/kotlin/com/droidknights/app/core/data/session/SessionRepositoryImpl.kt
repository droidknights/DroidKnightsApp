package com.droidknights.app.core.data.session

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.data.session.mapper.toData
import com.droidknights.app.core.data.session.model.RoomResponse
import com.droidknights.app.core.data.session.model.SessionResponse
import com.droidknights.app.core.data.session.model.SpeakerResponse
import com.droidknights.app.core.datastore.session.api.SessionPreferencesDataSource
import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.datetime.LocalDateTime

internal class SessionRepositoryImpl(
    private val sessionDataSource: SessionPreferencesDataSource,
) : SessionRepository {

    private val bookmarkIds: Flow<Set<String>> = sessionDataSource.bookmarkedSession

    private val sessionResponses = listOf(
        SessionResponse(
            id = "1",
            title = "세션 타이틀 1",
            content = "세션 설명 세션 설명 세션 설명 세션 설명 세션 설명 세션 설명 ",
            speakers = listOf(
                SpeakerResponse(
                    name = "세션 연사자 1",
                    introduction = "세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1 세션 연사자 설명 1",
                    imageUrl = "https://picsum.photos/200",
                ),
            ),
            tags = listOf("Android", "iOS", "Desktop", "Web"),
            room = RoomResponse.TRACK1,
            startTime = LocalDateTime(2025, 6, 17, 11, 0),
            endTime = LocalDateTime(2025, 6, 17, 12, 0),
        ),
        SessionResponse(
            id = "2",
            title = "세션 타이틀 2",
            content = "세션 설명 세션 설명 세션 설명 세션 설명 세션 설명 세션 설명 ",
            speakers = listOf(
                SpeakerResponse(
                    name = "세션 연사자 2",
                    introduction = "세션 연사자 설명 2 세션 연사자 설명 2 세션 연사자 설명 2 세션 연사자 설명 2 세션 연사자 설명 2 세션 연사자 설명 2 세션 연사자 설명 2",
                    imageUrl = "https://picsum.photos/200",
                ),
            ),
            tags = listOf("Android", "iOS", "Desktop", "Web"),
            room = RoomResponse.TRACK2,
            startTime = LocalDateTime(2025, 6, 17, 12, 0),
            endTime = LocalDateTime(2025, 6, 17, 13, 0),
        ),
    )

    override suspend fun getSessions(): List<Session> {
        return sessionResponses.map { it.toData() }
    }

    override suspend fun getSession(sessionId: String): Session {
        return getSessions().first { it.id == sessionId } // TODO 없을 때 에러 처리
    }

    override fun getBookmarkedSessionIds(): Flow<Set<String>> = bookmarkIds

    override suspend fun bookmarkSession(sessionId: String, bookmark: Boolean) {
        val currentBookmarkedSessionIds = bookmarkIds.first()
        sessionDataSource.updateBookmarkedSession(
            if (bookmark) {
                currentBookmarkedSessionIds + sessionId
            } else {
                currentBookmarkedSessionIds - sessionId
            },
        )
    }

    override suspend fun deleteBookmarkedSessions(sessionIds: Set<String>) {
        val currentBookmarkedSessionIds = bookmarkIds.first()
        sessionDataSource.updateBookmarkedSession(
            currentBookmarkedSessionIds - sessionIds,
        )
    }
}
