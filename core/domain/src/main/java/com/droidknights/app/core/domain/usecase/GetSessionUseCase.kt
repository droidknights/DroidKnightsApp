package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.SessionRepository
import com.droidknights.app.core.model.Session
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(sessionId: String): Session {
        return sessionRepository.getSession(sessionId)
    }
}
