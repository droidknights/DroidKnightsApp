package com.droidknights.app.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.js.Js

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Js) {
    config(this)
}
