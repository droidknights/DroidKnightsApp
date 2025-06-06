package com.droidknights.app.core.data.session

import com.droidknights.app.core.data.session.model.SessionResponse
import com.droidknights.app.core.network.DroidKnightsNetwork

internal class SessionApi(
    private val network: DroidKnightsNetwork,
) {
    suspend fun getSessions(): List<SessionResponse> =
        network.get("/droidknights/DroidKnightsApp/main/core/data/src/main/assets/sessions.json")
}
