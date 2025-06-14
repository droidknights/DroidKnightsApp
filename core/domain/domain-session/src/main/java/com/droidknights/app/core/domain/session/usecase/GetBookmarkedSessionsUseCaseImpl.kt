package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionIdsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetBookmarkedSessionsUseCase
import com.droidknights.app.core.domain.session.usecase.api.GetSessionsUseCase
import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

internal class GetBookmarkedSessionsUseCaseImpl @Inject constructor(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val getBookmarkedSessionIdsUseCase: GetBookmarkedSessionIdsUseCase,
) : GetBookmarkedSessionsUseCase {

    override operator fun invoke(): Flow<List<Session>> =
        combine(
            getSessionsUseCase(),
            getBookmarkedSessionIdsUseCase()
        ) { allSession, bookmarkedSessions ->
            allSession
                .filter { session -> bookmarkedSessions.contains(session.id) }
                .sortedBy { it.startTime }
        }
}
