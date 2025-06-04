package com.droidknights.app.core.network.api

import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo

interface DroidKnightsNetwork {
    suspend fun <T : Any> get(path: String, typeInfo: TypeInfo): T
}

suspend inline fun <reified T : Any> DroidKnightsNetwork.get(path: String): T =
    this.get(path, typeInfo<T>())
