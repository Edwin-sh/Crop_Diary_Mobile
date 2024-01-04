package com.myapps.cropdiarymobile.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.util.Utilities
import com.myapps.cropdiarymobile.data.state.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogViewModel @Inject constructor() :
    ViewModel() {
    var state: DialogState by mutableStateOf(DialogState())
        private set

    fun showDialog() {
        state = state.copy(isShowing = true)
    }
    fun hideDialog() {
        state = state.copy(isShowing = false)
    }
    fun setTitle(title: String) {
        //state = state.copy(title = title)
    }
    fun setMessage(message: String) {
        state = state.copy(message = message)
    }
}