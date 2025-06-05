package com.droidknights.app.core.network

import com.droidknights.app.core.network.api.DroidKnightsNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.reflect.TypeInfo

class DroidKnightsNetworkImpl(
    private val client: HttpClient,
) : DroidKnightsNetwork {
    override suspend fun <T : Any> get(path: String, typeInfo: TypeInfo): T =
        client.get(path).body(typeInfo)
}
