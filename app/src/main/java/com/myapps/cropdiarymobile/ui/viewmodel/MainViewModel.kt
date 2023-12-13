package com.myapps.cropdiarymobile.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.cropdiarymobile.data.auth.ProviderType
import com.myapps.cropdiarymobile.domain.preferences.auth.GetSignInProviderUseCase
import com.myapps.cropdiarymobile.domain.preferences.auth.PutSignInProviderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSignInProviderUseCase: GetSignInProviderUseCase

): ViewModel() {
    private val provider: MutableLiveData<ProviderType> = MutableLiveData()
    fun provider(): LiveData<ProviderType> =  provider

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                provider.postValue(getSignInProviderUseCase())
            }
        }
    }
}