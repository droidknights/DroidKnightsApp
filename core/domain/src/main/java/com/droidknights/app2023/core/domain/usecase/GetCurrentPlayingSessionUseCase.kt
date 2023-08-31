package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SessionRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetCurrentPlayingSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): String? {
        return sessionRepository.getCurrentPlayingSessionId().firstOrNull()
    }
}
