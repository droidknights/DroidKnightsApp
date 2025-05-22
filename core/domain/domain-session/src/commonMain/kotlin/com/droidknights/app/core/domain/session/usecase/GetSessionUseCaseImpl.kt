package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.domain.session.api.usecase.GetSessionUseCase
import com.droidknights.app.core.model.session.Session

internal class GetSessionUseCaseImpl(
    private val sessionRepository: SessionRepository,
) : GetSessionUseCase {

    override suspend operator fun invoke(sessionId: String): Session =
        sessionRepository.getSession(sessionId)
}
