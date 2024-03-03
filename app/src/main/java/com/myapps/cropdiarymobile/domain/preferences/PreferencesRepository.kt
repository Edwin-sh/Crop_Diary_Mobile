package com.myapps.cropdiarymobile.domain.preferences

import com.myapps.cropdiarymobile.data.auth.ProviderType

interface PreferencesRepository {
    suspend fun putOnBoardingState(value: Boolean)
    suspend fun getOnBoardingState(): Boolean?
    suspend fun putSignInProvider(value: ProviderType)
    suspend fun getSignInProvider(): ProviderType
    suspend fun clearSignInProvider(): Boolean
}