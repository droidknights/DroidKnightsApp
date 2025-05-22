package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.domain.session.api.usecase.DeleteBookmarkedSessionUseCase

internal class DeleteBookmarkedSessionUseCaseImpl(
    private val sessionRepository: SessionRepository,
) : DeleteBookmarkedSessionUseCase {

    override suspend operator fun invoke(sessionIds: Set<String>) =
        sessionRepository.deleteBookmarkedSessions(sessionIds)
}
