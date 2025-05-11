package com.droidknights.app.core.data.api.fake

import com.droidknights.app.core.data.api.GithubRawApi
import com.droidknights.app.core.data.api.model.ContributionYearResponse
import com.droidknights.app.core.data.api.model.SessionResponse
import com.droidknights.app.core.data.api.model.SponsorResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

@OptIn(ExperimentalSerializationApi::class)
internal class AssetsGithubRawApi(
    private val json: Json,
    private val sponsors: InputStream,
    private val sessions: InputStream,
    private val contributors: InputStream,
) : GithubRawApi {

    override suspend fun getSponsors(): List<SponsorResponse> {
        return json.decodeFromStream(sponsors)
    }

    override suspend fun getSessions(): List<SessionResponse> {
        return json.decodeFromStream(sessions)
    }

    override suspend fun getContributorWithYears(): List<ContributionYearResponse> {
        return json.decodeFromStream(contributors)
    }
}
