package com.myapps.cropdiarymobile.data.repository

import com.myapps.cropdiarymobile.core.util.PreferencesConstants.ON_BOARDING_STATE
import com.myapps.cropdiarymobile.core.util.PreferencesConstants.SIGNIN_PROVIDER
import com.myapps.cropdiarymobile.data.auth.ProviderType
import com.myapps.cropdiarymobile.data.source.preferences.AuthPreferences
import com.myapps.cropdiarymobile.data.source.preferences.OnBoardingPreferences
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import javax.inject.Inject


class PreferencesRepositoryImpl @Inject constructor(
    private val onBoardingPreferences: OnBoardingPreferences,
    private val authPreferences: AuthPreferences
) : PreferencesRepository {
    override suspend fun putOnBoardingState(value: Boolean) {
        return onBoardingPreferences.putOnBoardingState(ON_BOARDING_STATE, value)
    }

    override suspend fun getOnBoardingState(): Boolean? {
        return onBoardingPreferences.getOnBoardingState(ON_BOARDING_STATE)
    }

    override suspend fun putSignInProvider(value: ProviderType) {
        return authPreferences.putSignInProvider(SIGNIN_PROVIDER, value)
    }

    override suspend fun getSignInProvider(): ProviderType {
        return authPreferences.getSignInProvider(SIGNIN_PROVIDER)
    }

    override suspend fun clearSignInProvider() {
        return authPreferences.clearSignInProvider(SIGNIN_PROVIDER)
    }
}