package com.myapps.cropdiarymobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.cropdiarymobile.data.state.OnBoardingState
import com.myapps.cropdiarymobile.domain.preferences.onboarding.GetOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getOnBoardingStateUseCase: GetOnBoardingStateUseCase
) : ViewModel() {
    var state by mutableStateOf(OnBoardingState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            delay(2000)
            val result = getOnBoardingStateUseCase()
            Log.i("TAG", "SplashViewModel: se recibió  $result")
            state = state.copy(
                isComplete = getOnBoardingStateUseCase() == true,
                isLoading = false
            )
        }
    }
}