package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.domain.session.api.usecase.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetBookmarkedSessionsUseCase
import com.droidknights.app.core.domain.session.api.usecase.GetSessionsUseCase
import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class GetBookmarkedSessionsUseCaseImpl(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
) : GetBookmarkedSessionsUseCase {

    override operator fun invoke(): Flow<List<Session>> =
        combine(
            getSessionsUseCase(),
            getBookmarkedSessionIdsUseCase(),
        ) { allSession, bookmarkedSessions ->
            allSession
                .filter { session -> bookmarkedSessions.contains(session.id) }
                .sortedBy { it.startTime }
        }
}
