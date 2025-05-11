package com.droidknights.app.core.data.api.fake

import com.droidknights.app.core.data.api.GithubRawApi
import com.droidknights.app.core.data.api.model.ContributionYearResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

@OptIn(ExperimentalSerializationApi::class)
internal class AssetsGithubRawApi(
    private val json: Json,
    private val contributors: InputStream,
) : GithubRawApi {

    override suspend fun getContributorWithYears(): List<ContributionYearResponse> {
        return json.decodeFromStream(contributors)
    }
}
