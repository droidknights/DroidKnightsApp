package com.droidknights.app2023.core.data.api.fake

import com.droidknights.app2023.core.data.api.GithubRawApi
import com.droidknights.app2023.core.data.api.model.SponsorResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

internal class FakeGithubRawApi(
    private val json: Json = Json { ignoreUnknownKeys = true },
) : GithubRawApi {
    private val assets = File("src/main/assets/sponsors.json")
    
    override suspend fun getSponsors(): List<SponsorResponse> {
        return json.decodeFromStream(assets.inputStream())
    }
}
