package com.droidknights.app2023.core.domain.usecase.api

import com.droidknights.app2023.core.model.Session

interface GetSessionUseCase {
    suspend operator fun invoke(sessionId: String): Session
}
