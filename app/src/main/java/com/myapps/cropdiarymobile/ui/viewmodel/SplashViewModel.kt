package com.myapps.cropdiarymobile.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.cropdiarymobile.data.repository.PreferencesRepositoryImpl
import com.myapps.cropdiarymobile.domain.preferences.GetOnBoardingStateUseCase
import com.myapps.cropdiarymobile.ui.navigation.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getOnBoardingStateUseCase: GetOnBoardingStateUseCase
):ViewModel() {
    var isLoading: MutableState<Boolean> = mutableStateOf(true)
        private set

    var continueDestination: MutableState<String> = mutableStateOf(Destinations.OnBoardingScreen.route)
        private set

    init {
        viewModelScope.launch {
            continueDestination.value = getOnBoardingStateUseCase().let {
                if(it==true) {
                    Destinations.SignInScreen.route
                }else {
                    Destinations.OnBoardingScreen.route
                }
            }
            isLoading.value = false
        }
    }
}