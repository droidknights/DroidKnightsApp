package com.droidknights.app.core.domain.session.usecase

import com.droidknights.app.core.data.session.api.SessionRepository
import com.droidknights.app.core.domain.session.usecase.api.GetSessionsUseCase
import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetSessionsUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
) : GetSessionsUseCase {

    override operator fun invoke(): Flow<List<Session>> =
        flow {
            emit(sessionRepository.getSessions())
        }
}
