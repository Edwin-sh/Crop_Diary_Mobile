package com.myapps.cropdiarymobile.data.source.preferences

interface OnBoardingPreferences {
    suspend fun putOnBoardingState(key: String, value: Boolean)
    suspend fun getOnBoardingState(key: String): Boolean?
}