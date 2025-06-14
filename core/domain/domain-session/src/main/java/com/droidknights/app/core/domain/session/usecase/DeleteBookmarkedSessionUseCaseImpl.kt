package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.domain.session.usecase.api.DeleteBookmarkedSessionUseCase
import javax.inject.Inject

internal class DeleteBookmarkedSessionUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : DeleteBookmarkedSessionUseCase {

    override suspend operator fun invoke(sessionIds: Set<String>) =
        sessionRepository.deleteBookmarkedSessions(sessionIds)
}
