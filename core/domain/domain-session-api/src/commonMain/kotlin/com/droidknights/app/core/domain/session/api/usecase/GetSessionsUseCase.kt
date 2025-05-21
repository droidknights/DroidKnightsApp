package com.droidknights.app.core.domain.session.api.usecase

import com.droidknights.app.core.model.session.Session
import kotlinx.coroutines.flow.Flow

interface GetSessionsUseCase {

    operator fun invoke(): Flow<List<Session>>
}
