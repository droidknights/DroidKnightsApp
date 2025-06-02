package com.droidknights.app.core.network.di

import com.droidknights.app.core.network.DroidknightsNetworkImpl
import com.droidknights.app.core.network.api.DroidknightsNetwork
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

val networkModule = module {
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
                connectTimeoutMillis = 6000
                requestTimeoutMillis = 6000
                socketTimeoutMillis = 6000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                url {
                    protocol = URLProtocol.HTTPS
                    host = "raw.githubusercontent.com"
                }
            }
        }
    }
    single<DroidknightsNetwork> { DroidknightsNetworkImpl(get()) }
}
