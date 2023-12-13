package com.myapps.cropdiarymobile.domain.preferences.onboarding

import android.util.Log
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import javax.inject.Inject

class PutOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(value: Boolean){
        repository.putOnBoardingState(value)
    }
}