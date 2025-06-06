package com.droidknights.app.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin

internal actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
    config(this)
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}
