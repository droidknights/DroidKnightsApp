package com.droidknights.app2023.core.data.api.fake

import android.content.Context
import com.droidknights.app2023.core.data.api.GithubRawApi
import com.droidknights.app2023.core.data.api.model.SponsorResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

internal class AssetsGithubRawApi(
    context: Context,
    private val json: Json = Json { ignoreUnknownKeys = true },
) : GithubRawApi {
    private val assets = context.assets.open("sponsors.json")
    
    override suspend fun getSponsors(): List<SponsorResponse> {
        return json.decodeFromStream(assets)
    }
}
