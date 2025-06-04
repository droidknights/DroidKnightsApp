package com.droidknights.app.core.network.di

import com.droidknights.app.core.network.DroidKnightsNetworkImpl
import com.droidknights.app.core.network.api.DroidKnightsNetwork
import com.droidknights.app.core.network.di.NetworkDefaults.BASE_HOST
import com.droidknights.app.core.network.di.NetworkDefaults.TIMEOUT_MILLIS
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val coreNetworkModule = module {
    single {
        val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }
        HttpClient {
            install(ContentNegotiation) {
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
    }
    single<DroidKnightsNetwork> { DroidKnightsNetworkImpl(get()) }
}

internal object NetworkDefaults {
    const val TIMEOUT_MILLIS = 6_000L
    const val BASE_HOST = "raw.githubusercontent.com"
}
