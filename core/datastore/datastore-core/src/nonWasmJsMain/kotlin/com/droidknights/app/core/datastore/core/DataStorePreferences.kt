package com.droidknights.app.core.datastore.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("TooManyFunctions")
class DataStorePreferences(
    val dataStore: DataStore<Preferences>,
) : LocalPreferences {

    override fun getBoolean(key: String): Flow<Boolean?> = dataStore.data.map { preferences ->
        preferences[booleanPreferencesKey(key)]
    }

    override fun getInt(key: String): Flow<Int?> = dataStore.data.map { preferences ->
        preferences[intPreferencesKey(key)]
    }

    override fun getLong(key: String): Flow<Long?> = dataStore.data.map { preferences ->
        preferences[longPreferencesKey(key)]
    }

    override fun getFloat(key: String): Flow<Float?> = dataStore.data.map { preferences ->
        preferences[floatPreferencesKey(key)]
    }

    override fun getDouble(key: String): Flow<Double?> = dataStore.data.map { preferences ->
        preferences[doublePreferencesKey(key)]
    }

    override fun getString(key: String): Flow<String?> = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(key)]
    }

    override fun getStringSet(key: String): Flow<Set<String>?> = dataStore.data.map { preferences ->
        preferences[stringSetPreferencesKey(key)]
    }

    override fun getByteArray(key: String): Flow<ByteArray?> = dataStore.data.map { preferences ->
        preferences[byteArrayPreferencesKey(key)]
    }

    override suspend fun setBoolean(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun setInt(key: String, value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    override suspend fun setLong(key: String, value: Long) {
        dataStore.edit { preferences ->
            preferences[longPreferencesKey(key)] = value
        }
    }

    override suspend fun setFloat(key: String, value: Float) {
        dataStore.edit { preferences ->
            preferences[floatPreferencesKey(key)] = value
        }
    }

    override suspend fun setDouble(key: String, value: Double) {
        dataStore.edit { preferences ->
            preferences[doublePreferencesKey(key)] = value
        }
    }

    override suspend fun setString(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun setStringSet(
        key: String,
        value: Set<String>,
    ) {
        dataStore.edit { preferences ->
            preferences[stringSetPreferencesKey(key)] = value
        }
    }

    override suspend fun setByteArray(key: String, value: ByteArray) {
        dataStore.edit { preferences ->
            preferences[byteArrayPreferencesKey(key)] = value
        }
    }
}
