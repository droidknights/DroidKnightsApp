package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SessionRepository
import com.droidknights.app2023.core.model.Session
import javax.inject.Inject

class GetCurrentPlayingSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): Session? {
        return sessionRepository.getCurrentPlayingSession()
    }
}
