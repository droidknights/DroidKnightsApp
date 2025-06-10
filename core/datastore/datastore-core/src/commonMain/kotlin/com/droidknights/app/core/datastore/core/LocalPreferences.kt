package com.droidknights.app.core.datastore.core

import kotlinx.coroutines.flow.Flow

@Suppress("TooManyFunctions")
interface LocalPreferences {

    fun getBoolean(key: String): Flow<Boolean?>

    fun getInt(key: String): Flow<Int?>

    fun getLong(key: String): Flow<Long?>

    fun getFloat(key: String): Flow<Float?>

    fun getDouble(key: String): Flow<Double?>

    fun getString(key: String): Flow<String?>

    fun getStringSet(key: String): Flow<Set<String>?>

    fun getByteArray(key: String): Flow<ByteArray?>

    suspend fun setBoolean(key: String, value: Boolean)

    suspend fun setInt(key: String, value: Int)

    suspend fun setLong(key: String, value: Long)

    suspend fun setFloat(key: String, value: Float)

    suspend fun setDouble(key: String, value: Double)

    suspend fun setString(key: String, value: String)

    suspend fun setStringSet(key: String, value: Set<String>)

    suspend fun setByteArray(key: String, value: ByteArray)
}
