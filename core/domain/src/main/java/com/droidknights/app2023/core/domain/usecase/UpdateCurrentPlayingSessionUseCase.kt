package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SessionRepository
import javax.inject.Inject

class UpdateCurrentPlayingSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(sessionId: String) {
        return sessionRepository.updateCurrentPlayingSessionId(sessionId)
    }
}
