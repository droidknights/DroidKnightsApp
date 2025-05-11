package com.droidknights.app.core.data.contributor.api.fake

import com.droidknights.app.core.data.contributor.api.DroidnightsContributorsApi
import com.droidknights.app.core.data.contributor.model.ContributionYearResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

@OptIn(ExperimentalSerializationApi::class)
internal class AssetsDroidnightsContributorsApi(
    private val json: Json,
    private val contributors: InputStream,
) : DroidnightsContributorsApi {

    override suspend fun getContributorWithYears(url: String): List<ContributionYearResponse> =
        json.decodeFromStream(contributors)
}
