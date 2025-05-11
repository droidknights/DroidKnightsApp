package com.droidknights.app.core.data.api.fake

import com.droidknights.app.core.data.api.GithubRawApi
import com.droidknights.app.core.data.api.model.ContributionYearResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
internal class FakeGithubRawApi(
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    },
) : GithubRawApi {

    private val contributors = File("src/main/assets/contributors.json")

    override suspend fun getContributorWithYears(): List<ContributionYearResponse> {
        return json.decodeFromStream(contributors.inputStream())
    }
}
