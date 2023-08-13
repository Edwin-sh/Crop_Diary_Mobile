package com.myapps.cropdiarymobile.domain.preferences

interface PreferencesRepository {
    suspend fun putOnBoardingState(value: Boolean)
    suspend fun getOnBoardingState(): Boolean?
}