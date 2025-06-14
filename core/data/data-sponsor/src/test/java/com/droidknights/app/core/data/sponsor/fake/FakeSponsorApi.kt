package com.droidknights.app.core.data.sponsor.fake

import com.droidknights.app.core.data.sponsor.api.SponsorApi
import com.droidknights.app.core.data.sponsor.model.SponsorResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
internal class FakeSponsorApi(
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    },
) : SponsorApi {

    private val sponsors = File("src/test/assets/sponsors.json")
    override suspend fun getSponsors(url: String): List<SponsorResponse> =
        json.decodeFromStream(sponsors.inputStream())
}
