package com.myapps.cropdiarymobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.myapps.cropdiarymobile.domain.connection.GetConnectionStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor(
    private val getConnectionStateUseCase: GetConnectionStateUseCase,
) : ViewModel() {
    fun getConnectionState(): Boolean {
        return getConnectionStateUseCase()
    }
}