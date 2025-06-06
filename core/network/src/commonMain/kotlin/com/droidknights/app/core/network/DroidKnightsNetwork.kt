package com.droidknights.app.core.network

import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json

class DroidKnightsNetwork {
    val httpClient = httpClient {
        install(ContentNegotiation) {
            val json = Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            }
            register(ContentType.Application.Json, KotlinxSerializationConverter(json))
            register(ContentType.Text.Plain, KotlinxSerializationConverter(json))
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT_MILLIS
            requestTimeoutMillis = TIMEOUT_MILLIS
            socketTimeoutMillis = TIMEOUT_MILLIS
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_HOST
            }
        }
    }

    suspend inline fun <reified T : Any> get(path: String): T = httpClient.get(path).body()

    companion object {
        private const val TIMEOUT_MILLIS = 6_000L
        private const val BASE_HOST = "raw.githubusercontent.com"
    }
}
