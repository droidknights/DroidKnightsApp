package com.droidknights.app.core.network.engine

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun provideHttpClientEngine(): HttpClientEngine = Darwin.create()
