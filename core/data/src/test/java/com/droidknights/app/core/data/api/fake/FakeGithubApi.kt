package com.droidknights.app.core.data.api.fake

import com.droidknights.app.core.data.api.GithubApi
import com.droidknights.app.core.data.api.model.CommitResponse
import com.droidknights.app.core.data.api.model.ContributorResponse

internal class FakeGithubApi(
    private val contributors: List<ContributorResponse>,
    private val commits: List<CommitResponse>
) : GithubApi {
    override suspend fun getContributors(owner: String, name: String): List<ContributorResponse> {
        return contributors
    }

    override suspend fun getCommits(
        owner: String,
        name: String,
        author: String,
        perPage: Int
    ): List<CommitResponse> {
        return commits
    }
}
