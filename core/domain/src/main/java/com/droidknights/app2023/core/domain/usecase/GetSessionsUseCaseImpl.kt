package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.api.SessionRepository
import com.droidknights.app2023.core.domain.usecase.api.GetSessionsUseCase
import com.droidknights.app2023.core.model.Session
import javax.inject.Inject

internal class GetSessionsUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetSessionsUseCase {
    override suspend operator fun invoke(): List<Session> {
        return sessionRepository.getSessions()
    }
}
