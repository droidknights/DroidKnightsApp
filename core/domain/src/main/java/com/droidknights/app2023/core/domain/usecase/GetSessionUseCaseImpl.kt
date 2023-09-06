package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.api.SessionRepository
import com.droidknights.app2023.core.domain.usecase.api.GetSessionUseCase
import com.droidknights.app2023.core.model.Session
import javax.inject.Inject

internal class GetSessionUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetSessionUseCase {

    override suspend operator fun invoke(sessionId: String): Session {
        return sessionRepository.getSession(sessionId)
    }
}
