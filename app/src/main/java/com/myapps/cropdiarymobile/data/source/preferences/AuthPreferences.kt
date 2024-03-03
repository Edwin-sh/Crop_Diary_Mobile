package com.myapps.cropdiarymobile.data.source.preferences

import com.myapps.cropdiarymobile.data.auth.ProviderType

interface AuthPreferences {
    suspend fun putSignInProvider(key: String, value: ProviderType)
    suspend fun getSignInProvider(key: String): ProviderType
    suspend fun clearSignInProvider(key: String): Boolean
}