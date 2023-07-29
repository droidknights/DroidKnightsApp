package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SessionRepository
import com.droidknights.app2023.core.model.Session
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(sessionId: String): Session {
        // TODO: Session 데이터 연결 후 제거
        return GetSessionsUseCase(sessionRepository).invoke().first { it.id == sessionId }
        return sessionRepository.getSession(sessionId)
    }
}
