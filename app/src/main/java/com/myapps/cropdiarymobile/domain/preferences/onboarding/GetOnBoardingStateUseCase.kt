package com.myapps.cropdiarymobile.domain.preferences.onboarding

import android.util.Log
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import javax.inject.Inject

class GetOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(): Boolean? {
        return repository.getOnBoardingState()
    }
}