package com.droidknights.app2023.core.data.api

import com.droidknights.app2023.core.data.api.model.SponsorResponse
import retrofit2.http.GET

internal interface GithubRawApi {
    
    @GET("/droidknights/DroidKnights2023_App/feature/#50/data/src/main/assets/sponsors.json")
    suspend fun getSponsors(): List<SponsorResponse>
}
