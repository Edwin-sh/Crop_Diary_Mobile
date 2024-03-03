package com.myapps.cropdiarymobile.ui.viewmodel.screenStates

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.util.Utilities
import com.myapps.cropdiarymobile.data.state.SignInScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginScreenStateViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) :
    ViewModel() {
    var state: SignInScreenState by mutableStateOf(SignInScreenState())
        private set

    fun setEmail(email: String) {
        state = state.copy(email = email)
        if (email == "") {
            setEmailError(context.getString(R.string.you_must_enter_the_email))
        } else if (!Utilities.validateEmail(email)) {
            setEmailError(context.getString(R.string.you_must_enter_a_valid_email))
        } else deleteEmailError()
    }

    private fun setEmailError(error: String) {
        state = state.copy(emailError = error)
    }

    private fun deleteEmailError() {
        state = state.copy(emailError = "")
    }

    fun setPassword(password: String) {
        state = state.copy(password = password)
        if (password == "") {
            setPasswordError(context.getString(R.string.you_must_enter_the_email))
        } else if (password.length < 8) {
            setPasswordError(context.getString(R.string.the_password_must_be_at_least_8_digits))
        } else if (!Utilities.validatePassword(password)) {
            setPasswordError(context.getString(R.string.password_must_have_at_least_one_digit_and_one_uppercase_letter))
        } else deletePasswordError()
    }

    fun showPassword(show: Boolean) {
        state = state.copy(isPasswordVisible = show)
    }

    private fun setPasswordError(error: String) {
        state = state.copy(passwordError = error)
    }

    private fun deletePasswordError() {
        state = state.copy(passwordError = "")
    }

    fun setLoginButtonEnabled(enabled: Boolean) {
        state = state.copy(isLoginButtonEnabled = enabled)
    }
}