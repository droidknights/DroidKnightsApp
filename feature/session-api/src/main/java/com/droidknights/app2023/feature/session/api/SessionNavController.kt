package com.droidknights.app2023.feature.session.api

import com.droidknights.app2023.feature.nav.DroidKnightsNavController

interface SessionNavController : DroidKnightsNavController<Unit>

interface SessionDetailNavController : DroidKnightsNavController<SessionDetailNavControllerInfo>

data class SessionDetailNavControllerInfo(
    val sessionId: String,
)
