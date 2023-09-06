package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.domain.usecase.api.GetBookmarkedSessionIdsUseCase
import com.droidknights.app2023.core.domain.usecase.api.GetBookmarkedSessionsUseCase
import com.droidknights.app2023.core.domain.usecase.api.GetSessionsUseCase
import com.droidknights.app2023.core.model.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetBookmarkedSessionsUseCaseImpl @Inject constructor(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
) : GetBookmarkedSessionsUseCase {

    override suspend operator fun invoke(): Flow<List<Session>> {
        return flow {
            emit(getSessionsUseCase())
        }.combine(getBookmarkedSessionIdsUseCase()) { allSession, bookmarkedSessions ->
            allSession
                .filter { session -> bookmarkedSessions.contains(session.id) }
                .sortedBy { it.startTime }
        }
    }
}
