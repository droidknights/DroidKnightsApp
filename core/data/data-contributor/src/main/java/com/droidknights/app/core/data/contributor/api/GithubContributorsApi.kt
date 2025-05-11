package com.droidknights.app.core.data.contributor.api

import com.droidknights.app.core.data.contributor.model.ContributorResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubContributorsApi {

    @GET("repos/{owner}/{name}/contributors")
    suspend fun getContributors(
        @Path("owner") owner: String,
        @Path("name") name: String,
    ): List<ContributorResponse>
}
