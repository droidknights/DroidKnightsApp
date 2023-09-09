package com.droidknights.app2023.feature.bookmark.usecase

import com.droidknights.app2023.core.repo.session.api.SessionRepository
import com.droidknights.app2023.core.repo.session.api.model.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal interface GetBookmarkedSessionsUseCase {
    suspend operator fun invoke(): Flow<List<Session>>
}

internal class GetBookmarkedSessionsUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetBookmarkedSessionsUseCase {

    override suspend operator fun invoke(): Flow<List<Session>> {
        return flow {
            emit(sessionRepository.getSessions())
        }.combine(sessionRepository.getBookmarkedSessionIds()) { allSession, bookmarkedSessions ->
            allSession
                .filter { session -> bookmarkedSessions.contains(session.id) }
                .sortedBy { it.startTime }
        }
    }
}
