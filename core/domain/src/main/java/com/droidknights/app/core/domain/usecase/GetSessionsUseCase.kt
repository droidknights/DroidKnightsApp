package com.droidknights.app.core.domain.usecase

import com.droidknights.app.core.data.repository.SessionRepository
import com.droidknights.app.core.model.Session
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSessionsUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): Flow<List<Session>> {
        return flow {
            emit(sessionRepository.getSessions())
        }
    }
}
