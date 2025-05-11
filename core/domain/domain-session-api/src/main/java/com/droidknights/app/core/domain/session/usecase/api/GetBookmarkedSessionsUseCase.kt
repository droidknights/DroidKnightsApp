package com.droidknights.app.core.domain.session.usecase.api

import com.droidknights.app.core.model.Session
import kotlinx.coroutines.flow.Flow

interface GetBookmarkedSessionsUseCase {

    operator fun invoke(): Flow<List<Session>>
}
