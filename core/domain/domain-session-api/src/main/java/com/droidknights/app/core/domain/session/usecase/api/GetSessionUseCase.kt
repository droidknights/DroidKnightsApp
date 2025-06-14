package com.droidknights.app.core.domain.session.usecase.api

import com.droidknights.app.core.model.session.Session

interface GetSessionUseCase {

    suspend operator fun invoke(sessionId: String): Session
}
