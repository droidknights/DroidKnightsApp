package com.droidknights.app.core.data.sponsor.api

import com.droidknights.app.core.data.sponsor.model.SponsorResponse
import retrofit2.http.GET
import retrofit2.http.Url

internal interface SponsorApi {

    @GET
    suspend fun getSponsors(
        @Url url: String,
    ): List<SponsorResponse>
}
