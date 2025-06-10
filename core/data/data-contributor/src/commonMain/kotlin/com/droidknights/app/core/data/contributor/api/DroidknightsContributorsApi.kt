package com.droidknights.app.core.data.contributor.api

import com.droidknights.app.core.data.contributor.model.ContributionYearResponse
import com.droidknights.app.core.network.DroidKnightsNetwork

internal class DroidknightsContributorsApi(
    private val network: DroidKnightsNetwork,
) {
    suspend fun getContributorWithYears(): List<ContributionYearResponse> =
        network.get("/droidknights/DroidKnightsApp/refs/heads/2025/app/assets/contributors.json")
}
