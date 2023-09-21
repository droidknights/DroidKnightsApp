package com.droidknights.app2023.feature.session.usecase

import com.droidknights.app2023.core.repo.session.api.SessionRepository
import com.droidknights.app2023.core.repo.session.api.model.Session
import javax.inject.Inject

internal interface GetSessionUseCase {
    suspend operator fun invoke(sessionId: String): Session
}

internal class GetSessionUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetSessionUseCase {

    override suspend operator fun invoke(sessionId: String): Session {
        return sessionRepository.getSession(sessionId)
    }
}
