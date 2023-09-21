package com.droidknights.app2023.feature.session.usecase

import com.droidknights.app2023.core.repo.session.api.SessionRepository
import com.droidknights.app2023.core.repo.session.api.model.Session
import javax.inject.Inject

internal interface GetSessionsUseCase {
    suspend operator fun invoke(): List<Session>
}

internal class GetSessionsUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetSessionsUseCase {
    override suspend operator fun invoke(): List<Session> {
        return sessionRepository.getSessions()
    }
}
