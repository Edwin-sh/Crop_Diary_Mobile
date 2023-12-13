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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.components.BasicButton
import com.myapps.cropdiarymobile.ui.navigation.Destinations
import com.myapps.cropdiarymobile.ui.navigation.LocalNavController
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.CreateAccountText
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ForgotPasswordText
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.LayoutId
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ScreenTitle
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SeparatorSingInMethods
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ShowPasswordOption
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SignInIcons
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SignInTextField
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.loginConstraints
import com.myapps.cropdiarymobile.ui.theme.CropDiaryAppTheme
import com.myapps.cropdiarymobile.ui.viewmodel.AuthViewModel

@Composable
fun SignInScreen(authViewModel: AuthViewModel = hiltViewModel()) {
    val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (showPassword, onShowPasswordChange) = rememberSaveable { mutableStateOf(false) }
    val navController = LocalNavController.current
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val firebaseUserModel = authViewModel.authSignInModel.observeAsState(null)
    val context = LocalContext.current as Activity


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(grid.margin)
    ) {
        if (firebaseUserModel.value != null) {
            Log.i("SignInScreen", "Firebase user signed in")
            navController.popBackStack()
            navController.navigate(Destinations.MainScreen.route)
        }else{
            Log.i("SignInScreen", "Firebase user signed in failled")
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
                    Log.i("SignInScreen", "Google clicked")
                    authViewModel.signInWithGoogle(context)
                },
                onPhoneClick = {}
            )
            SeparatorSingInMethods(modifier = Modifier.layoutId(LayoutId.separator))
            SignInTextField(
                value = email,
                onValueChange = { onEmailChange },
                label = stringResource(R.string.e_mail),
                icon = Default.Email,
                modifier = Modifier.layoutId(LayoutId.email_input)
            )
            SignInTextField(
                value = password,
                onValueChange = { onPasswordChange },
                label = stringResource(R.string.password),
                icon = Default.Lock,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.layoutId(LayoutId.password_input)
            )

            ShowPasswordOption(
                showPassword = showPassword,
                modifier = Modifier.layoutId(LayoutId.check_box),
                onShowPasswordChange = { onShowPasswordChange }
            )

            BasicButton(
                onClick = { Log.i("SignInScreen", "Login clicked") },
                text = stringResource(R.string.login),
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

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun Preview() {
    CropDiaryAppTheme {
        SignInScreen()
    }
}
