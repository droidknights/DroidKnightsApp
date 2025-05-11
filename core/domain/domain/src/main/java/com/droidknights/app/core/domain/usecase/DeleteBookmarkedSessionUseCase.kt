package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.SessionRepository
import javax.inject.Inject

class DeleteBookmarkedSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(sessionIds: Set<String>) =
        sessionRepository.deleteBookmarkedSessions(sessionIds)
}
