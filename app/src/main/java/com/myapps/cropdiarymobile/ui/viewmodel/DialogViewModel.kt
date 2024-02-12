package com.myapps.cropdiarymobile.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.data.state.DialogStateData
import com.myapps.cropdiarymobile.di.coroutines.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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

    init {
        clearDialogData()
    }

    private fun setData(
        title: String = "",
        message: String = "",
        positiveButtonText: String = "",
        positiveButtonAction: () -> Unit = {}
    ) {
        title.takeIf { it.isNotEmpty() }?.let { setTitle(it) }
        message.takeIf { it.isNotEmpty() }?.let { setTitle(it) }
        setPositiveButton(positiveButtonText, positiveButtonAction)
    }

    fun showErrorDialog(message: String) {
        setData(
            title = context.getString(R.string.error),
            message = message
        )
        state = state.copy(isShowing = true)
    }

    fun showSuccessDialog() {
        setTitle(context.getString(R.string.success))
        state = state.copy(isShowing = true)
    }



    fun showDialog() {
        state = state.copy(isShowing = true)
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

    private fun clearDialogData() {
        state = state.copy { dialogState }
    }


    private fun setPositiveButton(
        text: String = "",
        onClick: () -> Unit = {}
    ) {
        if (text.isNotEmpty()) {
            state = state.copy(
                positiveButtonText = text
            )
        }
        if (onClick != {}) {
            state = state.copy(
                positiveButtonAction = onClick
            )
        }
        state = state.copy(
            positiveButtonText = text,
            positiveButtonAction = onClick
        )
    }
}