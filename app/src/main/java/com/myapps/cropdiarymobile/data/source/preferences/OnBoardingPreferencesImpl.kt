package com.myapps.cropdiarymobile.data.source.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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
        return dataStore.data.first()[booleanPreferencesKey(key)]
    }
}