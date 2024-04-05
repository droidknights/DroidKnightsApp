package com.droidknights.app2023.core.data.api

import com.droidknights.app2023.core.data.api.model.SessionResponse
import com.droidknights.app2023.core.data.api.model.SponsorResponse
import retrofit2.http.GET

internal interface GithubRawApi {

    @GET("/workspace/DroidKnights2023-app-with-media3/media3-main/core/data/src/main/assets/sponsors.json")
    suspend fun getSponsors(): List<SponsorResponse>

    @GET("/workspace/DroidKnights2023-app-with-media3/media3-main/core/data/src/main/assets/sessions.json")
    suspend fun getSessions(): List<SessionResponse>
}
