package com.droidknights.app.core.data.session.api

import com.droidknights.app.core.data.session.model.SessionResponse
import retrofit2.http.GET
import retrofit2.http.Url

internal interface SessionApi {

    @GET
    suspend fun getSessions(
        @Url url: String,
    ): List<SessionResponse>
}
