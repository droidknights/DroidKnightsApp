package com.droidknights.app2023.core.domain.usecase.api

import com.droidknights.app2023.core.model.Session

interface GetSessionsUseCase {
    suspend operator fun invoke(): List<Session>
}
