package com.droidknights.app.core.network

import com.droidknights.app.core.network.mock.CONTRIBUTORS_JSON
import com.droidknights.app.core.network.mock.CONTRIBUTORS_PATH
import com.droidknights.app.core.network.mock.SESSIONS_JSON
import com.droidknights.app.core.network.mock.SESSIONS_PATH
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(
    MockEngine { request ->
        when (request.method) {
            HttpMethod.Get -> {
                val response = GET_RESPONSE_MAP[request.url.encodedPath]
                    ?: return@MockEngine respondError(
                        status = HttpStatusCode.NotFound,
                        content = NOT_FOUND_ERROR_MESSAGE,
                    )
                respond(
                    content = response,
                    status = HttpStatusCode.OK,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString(),
                    ),
                )
            }
            else -> respondError(
                status = HttpStatusCode.NotFound,
                content = NOT_FOUND_ERROR_MESSAGE,
            )
        }
    },
) {
    config(this)
}

private const val NOT_FOUND_ERROR_MESSAGE =
    "core:network wasmJsMain httpClient에 mock response를 추가하세요."

private val GET_RESPONSE_MAP = mapOf(
    CONTRIBUTORS_PATH to CONTRIBUTORS_JSON,
    SESSIONS_PATH to SESSIONS_JSON,
)
