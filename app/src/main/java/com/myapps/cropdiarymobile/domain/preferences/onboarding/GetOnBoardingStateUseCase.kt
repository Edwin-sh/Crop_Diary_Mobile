package com.myapps.cropdiarymobile.domain.preferences.onboarding

import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import javax.inject.Inject

class GetOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(): Boolean {
        return try {
            repository.getOnBoardingState() == true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}