package com.droidknights.app.core.data.api

import com.droidknights.app.core.data.api.model.ContributionYearResponse
import com.droidknights.app.core.data.api.model.SessionResponse
import com.droidknights.app.core.data.api.model.SponsorResponse
import retrofit2.http.GET

internal interface GithubRawApi {

    @GET("/droidknights/DroidKnightsApp/main/core/data/src/main/assets/sponsors.json")
    suspend fun getSponsors(): List<SponsorResponse>

    @GET("/droidknights/DroidKnightsApp/main/core/data/src/main/assets/sessions.json")
    suspend fun getSessions(): List<SessionResponse>

    @GET("/droidknights/DroidKnightsApp/main/core/data/src/main/assets/contributors.json")
    suspend fun getContributorWithYears(): List<ContributionYearResponse>
}
