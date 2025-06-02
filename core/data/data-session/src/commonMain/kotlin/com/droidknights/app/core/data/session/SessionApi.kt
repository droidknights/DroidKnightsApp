package com.droidknights.app.core.data.session

import com.droidknights.app.core.data.session.model.SessionResponse
import com.droidknights.app.core.network.DroidknightsNetwork
import com.droidknights.app.core.network.get

class SessionApi(
    private val network: DroidknightsNetwork
) {
    suspend fun getSessions(): List<SessionResponse> =
        network.get("/droidknights/DroidKnightsApp/main/core/data/src/main/assets/sessions.json")
}
