package com.droidknights.app.core.data.contributor.api

import com.droidknights.app.core.data.contributor.model.ContributionYearResponse
import retrofit2.http.GET
import retrofit2.http.Url

internal interface DroidnightsContributorsApi {

    @GET
    suspend fun getContributorWithYears(
        @Url url: String,
    ): List<ContributionYearResponse>
}
