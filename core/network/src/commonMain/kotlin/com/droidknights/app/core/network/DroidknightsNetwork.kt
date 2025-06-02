package com.droidknights.app.core.network

import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo

interface DroidknightsNetwork {
    suspend fun <T : Any> get(path: String, typeInfo: TypeInfo): T
}

suspend inline fun <reified T : Any> DroidknightsNetwork.get(path: String): T =
    this.get(path, typeInfo<T>())
