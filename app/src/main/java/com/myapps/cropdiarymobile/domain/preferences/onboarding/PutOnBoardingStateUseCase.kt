package com.myapps.cropdiarymobile.domain.preferences.onboarding

import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import javax.inject.Inject

class PutOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(value: Boolean): Boolean {
        return try {
            repository.putOnBoardingState(value)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}