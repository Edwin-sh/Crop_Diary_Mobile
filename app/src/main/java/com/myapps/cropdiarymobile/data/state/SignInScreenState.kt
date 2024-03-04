package com.myapps.cropdiarymobile.data.state

data class SignInScreenState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isSignInSuccess: Boolean = false,
    val emailError: String = "",
    val passwordError: String = "",
    val isLoginButtonEnabled: Boolean = false,
    val isUserNotFoundError: Boolean = false,
    val isUserDisabledError: Boolean = false,
    val isUserNotVerifiedError: Boolean = false,
    val isUserPasswordError: Boolean = false,
    val isUserPasswordExpiredError: Boolean = false,
    val isUserPasswordInvalidError: Boolean = false,
    val isUserPasswordTooShortError: Boolean = false,
    val isUserPasswordTooCommonError: Boolean = false,
    val isUserPasswordRequiresLowercaseError: Boolean = false,
    val isUserPasswordRequiresUppercaseError: Boolean = false,
    val isUserPasswordRequiresSymbolsError: Boolean = false,
    val isUserPasswordRequiresNumbersError: Boolean = false,
)
