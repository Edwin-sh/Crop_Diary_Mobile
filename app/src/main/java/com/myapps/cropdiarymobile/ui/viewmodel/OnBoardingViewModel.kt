package com.myapps.cropdiarymobile.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.cropdiarymobile.data.state.OnBoardingState
import com.myapps.cropdiarymobile.domain.preferences.onboarding.GetOnBoardingStateUseCase
import com.myapps.cropdiarymobile.domain.preferences.onboarding.PutOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnBoardingStateUseCase: GetOnBoardingStateUseCase,
    private val putOnBoardingStateUseCase: PutOnBoardingStateUseCase
) : ViewModel() {
    var state by mutableStateOf(OnBoardingState())
        private set

    init {
        getOnBoardingState()
    }

    fun getOnBoardingState() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            delay(2000)
            val resu = getOnBoardingStateUseCase()
           Log.i("TAG", "getOnBoardingState: ${resu}")
            state = state.copy(
                isComplete =  resu,
                isLoading = false
            )
        }
    }

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            putOnBoardingStateUseCase(completed)
        }
    }
}