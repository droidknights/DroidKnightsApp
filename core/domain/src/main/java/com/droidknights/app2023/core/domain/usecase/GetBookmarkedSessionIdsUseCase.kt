package com.droidknights.app2023.core.domain.usecase

import com.droidknights.app2023.core.data.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedSessionIdsUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(): List<String> {
        return sessionRepository.getBookmarkedSessionIds()
    }
}
