package com.myapps.cropdiarymobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.cropdiarymobile.domain.preferences.PutOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

 @HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val putOnBoardingStateUseCase: PutOnBoardingStateUseCase
) : ViewModel() {
    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            putOnBoardingStateUseCase(completed)
        }
    }
}