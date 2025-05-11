package com.droidknights.app.core.data.api

import com.droidknights.app.core.data.api.model.ContributionYearResponse
import retrofit2.http.GET

internal interface GithubRawApi {

    @GET("/droidknights/DroidKnightsApp/main/core/data/src/main/assets/contributors.json")
    suspend fun getContributorWithYears(): List<ContributionYearResponse>
}
