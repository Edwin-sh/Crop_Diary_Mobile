package com.myapps.cropdiarymobile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.cropdiarymobile.data.auth.ProviderType
import com.myapps.cropdiarymobile.domain.preferences.auth.GetLoginProviderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLoginProviderUseCase: GetLoginProviderUseCase

): ViewModel() {
    private val provider: MutableLiveData<ProviderType> = MutableLiveData()
    fun provider(): LiveData<ProviderType> =  provider

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                //provider.postValue(getSignInProviderUseCase())
            }
        }
    }
}