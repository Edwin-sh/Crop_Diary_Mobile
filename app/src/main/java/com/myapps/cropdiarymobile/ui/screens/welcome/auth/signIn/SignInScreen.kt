package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.components.BasicButton
import com.myapps.cropdiarymobile.ui.navigation.Destinations
import com.myapps.cropdiarymobile.ui.navigation.LocalNavController
import com.myapps.cropdiarymobile.ui.screens.splashScreen.components.ProgressIndicator
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.CreateAccountText
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ForgotPasswordText
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.LayoutId
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ScreenTitle
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SeparatorSingInMethods
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ShowPasswordOption
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SignInIcons
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SignInTextField
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.loginConstraints
import com.myapps.cropdiarymobile.ui.viewmodel.AuthViewModel
import com.myapps.cropdiarymobile.ui.viewmodel.ConnectionViewModel
import com.myapps.cropdiarymobile.ui.viewmodel.screenStates.SignInScreenStateViewModel

@Composable
fun SignInScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    signInScreenStateViewModel: SignInScreenStateViewModel = hiltViewModel(),
    connectionViewModel: ConnectionViewModel = hiltViewModel()
) {
    val authState = authViewModel.state
    val screenState = signInScreenStateViewModel.state
    val email = screenState.email
    val emailError = screenState.emailError
    val password = screenState.password
    val passwordError = screenState.passwordError
    val isPasswordVisible = screenState.isPasswordVisible
    val isLoginButtonEnabled = screenState.isLoginButtonEnabled
    val navController = LocalNavController.current
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val firebaseUserModel = authViewModel.authSignInModel.observeAsState(null)
    val context = LocalContext.current as Activity

    when (email != "" && password != "" && emailError == "" && passwordError == "") {
        true -> signInScreenStateViewModel.setLoginButtonEnabled(true)
        false -> signInScreenStateViewModel.setLoginButtonEnabled(false)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(grid.margin)
    ) {
        if (firebaseUserModel.value != null) {
            Log.i("SignInScreen", "Firebase user signed in")
            navController.popBackStack()
            navController.navigate(Destinations.MainScreen.route)
        } else {
            Log.i("SignInScreen", "Firebase user signed in failled")
        }
        if (authState.isLoading) {
            ProgressIndicator(
                orientation = orientation,
                grid = grid,
                modifier = Modifier
                    .layoutId(com.myapps.cropdiarymobile.ui.screens.splashScreen.LayoutId.progressIndicator)
            )
        }
        ConstraintLayout(
            constraintSet = loginConstraints(
                windowOrientation = orientation,
                grid = grid
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenTitle(modifier = Modifier.layoutId(LayoutId.title))
            SignInIcons(modifier = Modifier.layoutId(LayoutId.social_media_buttons),
                onFacebookClick = { Log.i("SignInScreen", "Facebook clicked") },
                onGoogleClick = {
                    if (connectionViewModel.getConnectionState())
                        authViewModel.signInWithGoogle(context)
                },
                onPhoneClick = {}
            )
            SeparatorSingInMethods(modifier = Modifier.layoutId(LayoutId.separator))
            SignInTextField(
                value = email,
                onValueChange = { signInScreenStateViewModel.setEmail(it) },
                label = stringResource(R.string.e_mail),
                errorMessage = emailError,
                icon = Default.Email,
                modifier = Modifier.layoutId(LayoutId.email_input)
            )
            SignInTextField(
                value = password,
                onValueChange = { signInScreenStateViewModel.setPassword(it) },
                label = stringResource(R.string.password),
                errorMessage = passwordError,
                icon = Default.Lock,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.layoutId(LayoutId.password_input)
            )

            ShowPasswordOption(
                showPassword = isPasswordVisible,
                modifier = Modifier.layoutId(LayoutId.check_box),
                onShowPasswordChange = { signInScreenStateViewModel.showPassword(it) }
            )

            BasicButton(
                onClick = { if (connectionViewModel.getConnectionState()){

                }
                                                                      },
                text = stringResource(R.string.login),
                enabled = isLoginButtonEnabled,
                modifier = Modifier.layoutId(LayoutId.login_button)
            )

            ForgotPasswordText(
                onClick = {},
                modifier = Modifier.layoutId(LayoutId.reset_password_text)
            )

            CreateAccountText(
                onClick = {},
                modifier = Modifier.layoutId(LayoutId.sign_up_text)
            )
        }
    }
}

