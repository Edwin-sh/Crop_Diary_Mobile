package com.myapps.cropdiarymobile.data.source.preferences

import com.myapps.cropdiarymobile.data.auth.ProviderType

interface OnBoardingPreferences {
    suspend fun putOnBoardingState(key: String, value: Boolean)
    suspend fun getOnBoardingState(key: String): Boolean?
}