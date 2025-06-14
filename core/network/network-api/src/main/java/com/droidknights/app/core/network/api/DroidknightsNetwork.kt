package com.droidknights.app.core.network.api

interface DroidknightsNetwork {

    fun <T> create(baseUrl: String, service: Class<T>): T
}

inline fun <reified T> DroidknightsNetwork.create(baseUrl: String): T {
    return create(baseUrl, T::class.java)
}
