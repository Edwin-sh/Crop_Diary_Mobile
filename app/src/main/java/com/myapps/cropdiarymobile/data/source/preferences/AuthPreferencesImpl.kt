package com.myapps.cropdiarymobile.data.source.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.myapps.cropdiarymobile.data.auth.ProviderType
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthPreferences {
    override suspend fun putSignInProvider(key: String, value: ProviderType) {
        val preferencesKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value.name
        }
    }

    override suspend fun getSignInProvider(key: String): ProviderType {
        val preferencesKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return ProviderType.valueOf(preferences[preferencesKey] ?: "")
    }

    override suspend fun clearSignInProvider(key: String) {
        dataStore.edit { preferences ->
            val preferencesKey = stringPreferencesKey(key)
            preferences[preferencesKey] = ""

        }
    }
}