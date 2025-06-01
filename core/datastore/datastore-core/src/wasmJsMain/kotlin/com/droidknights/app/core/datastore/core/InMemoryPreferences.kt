package com.droidknights.app.core.datastore.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

@Suppress("TooManyFunctions")
class InMemoryPreferences : MutableMap<String, MutableStateFlow<Any?>> by mutableMapOf(), LocalPreferences {

    override fun getBoolean(key: String): Flow<Boolean?> = getAsFlow<Boolean>(key)

    override fun getInt(key: String): Flow<Int?> = getAsFlow<Int>(key)

    override fun getLong(key: String): Flow<Long?> = getAsFlow<Long>(key)

    override fun getFloat(key: String): Flow<Float?> = getAsFlow<Float>(key)

    override fun getDouble(key: String): Flow<Double?> = getAsFlow<Double>(key)

    override fun getString(key: String): Flow<String?> = getAsFlow<String>(key)

    override fun getStringSet(key: String): Flow<Set<String>?> = getAsFlow<Set<String>>(key)

    override fun getByteArray(key: String): Flow<ByteArray?> = getAsFlow<ByteArray>(key)

    override suspend fun setBoolean(key: String, value: Boolean) {
        setAsFlow(key, value)
    }

    override suspend fun setInt(key: String, value: Int) {
        setAsFlow(key, value)
    }

    override suspend fun setLong(key: String, value: Long) {
        setAsFlow(key, value)
    }

    override suspend fun setFloat(key: String, value: Float) {
        setAsFlow(key, value)
    }

    override suspend fun setDouble(key: String, value: Double) {
        setAsFlow(key, value)
    }

    override suspend fun setString(key: String, value: String) {
        setAsFlow(key, value)
    }

    override suspend fun setStringSet(key: String, value: Set<String>) {
        setAsFlow(key, value)
    }

    override suspend fun setByteArray(key: String, value: ByteArray) {
        setAsFlow(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getAsFlow(key: String): Flow<T?> =
        getOrPut(key) { MutableStateFlow(null) }.asStateFlow().map { it as? T }

    private fun <T> setAsFlow(key: String, value: T) {
        getOrPut(key) { MutableStateFlow(value) }.value = value
    }
}
