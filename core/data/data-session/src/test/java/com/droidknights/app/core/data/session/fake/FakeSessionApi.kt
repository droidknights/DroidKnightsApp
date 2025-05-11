package com.droidknights.app.core.data.session.fake

import com.droidknights.app.core.data.session.api.SessionApi
import com.droidknights.app.core.data.session.model.SessionResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
internal class FakeSessionApi(
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    },
) : SessionApi {

    private val sponsors = File("src/test/assets/sessions.json")

    override suspend fun getSessions(url: String): List<SessionResponse> =
        json.decodeFromStream(sponsors.inputStream())
}
