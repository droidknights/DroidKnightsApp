package com.droidknights.app.core.network

import com.droidknights.app.core.network.api.DroidknightsNetwork
import retrofit2.Retrofit

class DroidknightsNetworkImpl(
    private val retrofit: Retrofit.Builder,
) : DroidknightsNetwork {

    override fun <T> create(baseUrl: String, service: Class<T>): T =
        retrofit
            .baseUrl(baseUrl)
            .build()
            .create(service)
}
