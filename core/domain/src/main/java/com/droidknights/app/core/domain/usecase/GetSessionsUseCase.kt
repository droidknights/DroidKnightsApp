package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.api.SessionRepository
import com.droidknights.app.core.model.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSessionsUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    operator fun invoke(): Flow<List<Session>> =
        flow {
            emit(sessionRepository.getSessions())
        }
}
