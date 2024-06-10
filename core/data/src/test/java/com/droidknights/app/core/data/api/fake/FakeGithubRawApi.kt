package com.droidknights.app.core.data.api.fake

import com.droidknights.app.core.data.api.GithubRawApi
import com.droidknights.app.core.data.api.model.ContributionYearResponse
import com.droidknights.app.core.data.api.model.SessionResponse
import com.droidknights.app.core.data.api.model.SponsorResponse
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

    private val sponsors = File("src/main/assets/sponsors.json")
    private val sessions = File("src/main/assets/sessions.json")
    private val contributors = File("src/main/assets/contributors.json")

    override suspend fun getSponsors(): List<SponsorResponse> {
        return json.decodeFromStream(sponsors.inputStream())
    }

    override suspend fun getSessions(): List<SessionResponse> {
        return json.decodeFromStream(sessions.inputStream())
    }

    override suspend fun getContributorWithYears(): List<ContributionYearResponse> {
        return json.decodeFromStream(contributors.inputStream())
    }
}
