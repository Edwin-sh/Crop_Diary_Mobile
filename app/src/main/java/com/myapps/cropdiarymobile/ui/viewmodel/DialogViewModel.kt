package com.myapps.cropdiarymobile.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.data.state.DialogStateData
import com.myapps.cropdiarymobile.di.coroutines.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DialogViewModel @Inject constructor(
    private val context: Context,
    private val dialogState: DialogStateData,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {
    var state: DialogStateData by mutableStateOf(dialogState)
        private set

    val error = context.getString(R.string.error)
    val info = context.getString(R.string.info)
    val success = context.getString(R.string.success)

    private fun setData(
        title: String = "",
        message: String = "",
        positiveButtonText: String = "",
        positiveButtonAction: () -> Unit = {},
        negativeButtonText: String = "",
        negativeButtonAction: () -> Unit = {}
    ) {
        title.takeIf { it.isNotEmpty() }?.let { setTitle(it) }
        message.takeIf { it.isNotEmpty() }?.let { setMessage(it) }
        setPositiveButton(positiveButtonText, positiveButtonAction)
        setNegativeButton(negativeButtonText, negativeButtonAction)
    }

    fun showDialog(
        title: String = success,
        message: String,
        positiveButtonText: String = context.getString(R.string.accept),
        positiveButtonAction: () -> Unit = {},
        negativeButtonText: String = "",
        negativeButtonAction: () -> Unit = {}
    ) {
        viewModelScope.launch(dispatcher) {
            setData(
                title,
                message,
                positiveButtonText,
                positiveButtonAction,
                negativeButtonText,
                negativeButtonAction
            )
            state = state.copy(isShowing = true)
        }
    }


    fun hideDialog() {
        state = state.copy(isShowing = false)
        clearDialogData()
    }

    private fun setTitle(title: String) {
        state = state.copy(title = title)
    }

    private fun setMessage(message: String) {
        state = state.copy(message = message)
    }

    private fun setPositiveButton(
        text: String = "",
        onClick: () -> Unit = {}
    ) {
        state = state.copy(
            positiveButtonText = text,
            positiveButtonAction = onClick
        )
    }

    private fun setNegativeButton(
        text: String = "",
        onClick: () -> Unit = {}
    ) {
        state = state.copy(
            negativeButtonText = text,
            negativeButtonAction = onClick
        )
    }

    fun clearDialogData() {
        state = state.copy(
            title = dialogState.title,
            message = dialogState.message,
            positiveButtonText = dialogState.positiveButtonText,
            positiveButtonAction = dialogState.positiveButtonAction
        )
    }
}