package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowGrid
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.components.BasicButton
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.LayoutId
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SignInTextField
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ForgotPasswordText
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.CreateAccountText
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ScreenTitle
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SeparatorSingInMethods
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.ShowPasswordOption
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.SignInIcons
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components.loginConstraints
import com.myapps.cropdiarymobile.ui.theme.CropDiaryAppTheme

@Composable
fun SignInScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    //val navController = LocalNavController.current
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(grid.margin)
    ) {
        ConstraintLayout(
            constraintSet = loginConstraints(
                windowOrientation = orientation,
                grid = grid
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenTitle(modifier = Modifier.layoutId(LayoutId.title))
            SignInIcons(modifier = Modifier.layoutId(LayoutId.social_media_buttons),
                onFacebookClick = {},
                onGoogleClick = {},
                onPhoneClick = {}
            )
            SeparatorSingInMethods(modifier = Modifier.layoutId(LayoutId.separator))
            SignInTextField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(R.string.e_mail),
                icon = Default.Email,
                modifier = Modifier.layoutId(LayoutId.email_input)
            )
            SignInTextField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(R.string.password),
                icon = Default.Lock,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.layoutId(LayoutId.password_input)
            )

            ShowPasswordOption(
                showPassword = showPassword,
                modifier = Modifier.layoutId(LayoutId.check_box)
            ) {
                showPassword = it
            }

            BasicButton(
                onClick = { /* Handle sign in */ },
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
