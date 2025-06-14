package com.droidknights.app.core.data.contributor.api.fake

import com.droidknights.app.core.data.contributor.api.GithubContributorsApi
import com.droidknights.app.core.data.contributor.model.ContributorResponse

internal class FakeGithubContributorsApi(
    private val contributors: List<ContributorResponse>,
) : GithubContributorsApi {

    override suspend fun getContributors(owner: String, name: String): List<ContributorResponse> {
        return contributors
    }
}
