package com.droidknights.app.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class DroidKnightsNetwork(
    val client: HttpClient
) {
    suspend inline fun <reified T : Any> get(path: String): T = client.get(path).body()
}
