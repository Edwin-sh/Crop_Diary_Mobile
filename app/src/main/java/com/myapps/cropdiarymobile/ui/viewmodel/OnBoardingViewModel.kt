package com.myapps.cropdiarymobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.cropdiarymobile.data.state.OnBoardingState
import com.myapps.cropdiarymobile.domain.preferences.onboarding.GetOnBoardingStateUseCase
import com.myapps.cropdiarymobile.domain.preferences.onboarding.PutOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnBoardingStateUseCase: GetOnBoardingStateUseCase,
    private val putOnBoardingStateUseCase: PutOnBoardingStateUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    var state by mutableStateOf(OnBoardingState())
        private set

    init {
        getOnBoardingState()
    }

    fun getOnBoardingState() {
        viewModelScope.launch(dispatcher) {
            state = state.copy(
                isLoading = true
            )
            delay(2000)
            state = state.copy(
                isComplete = getOnBoardingStateUseCase(),
                isLoading = false
            )
        }
    }

    fun saveOnBoardingState(completed: Boolean, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            putOnBoardingStateUseCase(completed)
        }
    }
}