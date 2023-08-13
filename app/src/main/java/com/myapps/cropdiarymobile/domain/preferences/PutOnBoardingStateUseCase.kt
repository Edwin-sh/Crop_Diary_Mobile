package com.myapps.cropdiarymobile.domain.preferences

import android.util.Log
import javax.inject.Inject

class PutOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(value: Boolean){
        repository.putOnBoardingState(value)
    }
}