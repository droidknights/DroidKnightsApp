package com.droidknights.app.core.network.api

interface DroidknightsNetwork {

    fun <T> create(baseUrl: String, service: Class<T>): T
}
