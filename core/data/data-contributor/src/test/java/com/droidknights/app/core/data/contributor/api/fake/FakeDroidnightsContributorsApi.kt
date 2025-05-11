package com.droidknights.app.core.data.contributor.api.fake

import com.droidknights.app.core.data.contributor.api.DroidnightsContributorsApi
import com.droidknights.app.core.data.contributor.model.ContributionYearResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
internal class FakeDroidnightsContributorsApi(
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    },
) : DroidnightsContributorsApi {

    private val contributors = File("src/test/assets/contributors.json")

    override suspend fun getContributorWithYears(url: String): List<ContributionYearResponse> =
        json.decodeFromStream(contributors.inputStream())
}
