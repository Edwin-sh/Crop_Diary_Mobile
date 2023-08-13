package com.myapps.cropdiarymobile.domain.preferences

import android.util.Log
import javax.inject.Inject

class GetOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(): Boolean? {
        return repository.getOnBoardingState()
    }
}