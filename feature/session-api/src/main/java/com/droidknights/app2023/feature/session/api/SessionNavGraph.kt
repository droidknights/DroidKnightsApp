package com.droidknights.app2023.feature.session.api

import com.droidknights.app2023.core.repo.session.api.model.Session
import com.droidknights.app2023.feature.nav.DroidKnightsNavGraph

interface SessionNavGraph : DroidKnightsNavGraph<SessionNavGraphInfo>

data class SessionNavGraphInfo(
    val onBackClick: () -> Unit,
    val onSessionClick: (Session) -> Unit,
    val onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
)
