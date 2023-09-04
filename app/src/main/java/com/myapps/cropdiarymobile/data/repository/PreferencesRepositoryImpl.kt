package com.myapps.cropdiarymobile.data.repository

import com.myapps.cropdiarymobile.core.util.PreferencesConstants.ON_BOARDING_STATE
import com.myapps.cropdiarymobile.data.source.preferences.OnBoardingPreferences
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import javax.inject.Inject


class PreferencesRepositoryImpl @Inject constructor(
    private val onBoardingPreferences: OnBoardingPreferences
) : PreferencesRepository {
    override suspend fun putOnBoardingState(value: Boolean) {
        return onBoardingPreferences.putOnBoardingState(ON_BOARDING_STATE, value)
    }

    override suspend fun getOnBoardingState(): Boolean? {
        return onBoardingPreferences.getOnBoardingState(ON_BOARDING_STATE)
    }
}