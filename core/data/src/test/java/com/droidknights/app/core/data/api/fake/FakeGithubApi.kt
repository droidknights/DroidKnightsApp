package com.droidknights.app.core.data.api.fake

import com.droidknights.app.core.data.api.GithubApi
import com.droidknights.app.core.data.api.model.ContributorResponse

internal class FakeGithubApi(private val contributors: List<ContributorResponse>) : GithubApi {
    override suspend fun getContributors(owner: String, name: String): List<ContributorResponse> {
        return contributors
    }
}
