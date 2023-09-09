package com.droidknights.app2023.core.sponsor.repo.network

import com.droidknights.app2023.core.sponsor.repo.network.model.SponsorResponse
import retrofit2.http.GET

internal interface SponsorApi {

    @GET("/droidknights/DroidKnights2023_App/main/core/data/src/main/assets/sponsors.json")
    suspend fun getSponsors(): List<SponsorResponse>
}
