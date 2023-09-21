package com.droidknights.app2023.core.repo.session.network

import com.droidknights.app2023.core.repo.session.network.model.SessionResponse
import retrofit2.http.GET

internal interface SessionApi {

    @GET("/droidknights/DroidKnights2023_App/main/core/data/src/main/assets/sessions.json")
    suspend fun getSessions(): List<SessionResponse>
}
