package com.droidknights.app.core.network.engine

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual fun provideHttpClientEngine(): HttpClientEngine = Js.create()
