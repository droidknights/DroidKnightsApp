package com.droidknights.app.core.data.session

import com.droidknights.app.core.data.session.model.SessionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SessionApi(private val client: HttpClient) {
    suspend fun getSessions(): List<SessionResponse> = client
        .get("/droidknights/DroidKnightsApp/main/core/data/src/main/assets/sessions.json")
        .body()
}
