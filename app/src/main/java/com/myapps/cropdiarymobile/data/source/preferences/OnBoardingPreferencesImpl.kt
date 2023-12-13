package com.myapps.cropdiarymobile.data.source.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.myapps.cropdiarymobile.data.auth.ProviderType
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class OnBoardingPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) :
    OnBoardingPreferences {
    override suspend fun putOnBoardingState(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getOnBoardingState(key: String): Boolean? {
        return try {
            val preferencesKey = booleanPreferencesKey(key)
            val preferences = dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}