package com.droidknights.app.core.network

import com.droidknights.app.core.network.api.DroidknightsNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.reflect.TypeInfo

class DroidknightsNetworkImpl(
    private val client: HttpClient
) : DroidknightsNetwork {
    override suspend fun <T : Any> get(path: String, typeInfo: TypeInfo): T =
        client.get(path).body(typeInfo)
}
