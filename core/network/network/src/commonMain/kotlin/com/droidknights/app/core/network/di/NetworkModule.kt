package com.droidknights.app.core.network.di

import com.droidknights.app.core.network.DroidKnightsNetworkImpl
import com.droidknights.app.core.network.api.DroidKnightsNetwork
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
                connectTimeoutMillis =  6_000
                requestTimeoutMillis =  6_000
                socketTimeoutMillis =  6_000
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
    single<DroidKnightsNetwork> { DroidKnightsNetworkImpl(get()) }
}
