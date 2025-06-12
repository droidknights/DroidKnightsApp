package com.droidknights.app.core.data.contributor.api

import com.droidknights.app.core.data.contributor.model.ContributorResponse
import com.droidknights.app.core.network.DroidKnightsNetwork

internal class GithubContributorsApi(
    private val network: DroidKnightsNetwork,
) {
    suspend fun getContributors(owner: String, name: String): List<ContributorResponse> =
        network.getFromGithub("/repos/$owner/$name/contributors")
}
