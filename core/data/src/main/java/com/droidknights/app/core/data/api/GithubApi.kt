package com.droidknights.app.core.data.api

import com.droidknights.app.core.data.api.model.CommitResponse
import com.droidknights.app.core.data.api.model.ContributorResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GithubApi {

    @GET("repos/{owner}/{name}/contributors")
    suspend fun getContributors(
        @Path("owner") owner: String,
        @Path("name") name: String,
    ): List<ContributorResponse>

    @GET("repos/{owner}/{name}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String,
        @Path("name") name: String,
        @Query("author") author: String,
        @Query("per_page") perPage: Int,
    ): List<CommitResponse>
}
