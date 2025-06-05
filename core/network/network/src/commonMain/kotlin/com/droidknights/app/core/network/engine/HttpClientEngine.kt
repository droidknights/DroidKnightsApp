package com.droidknights.app.core.network.engine

import io.ktor.client.engine.HttpClientEngine

expect fun provideHttpClientEngine(): HttpClientEngine
