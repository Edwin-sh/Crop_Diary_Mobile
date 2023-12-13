package com.myapps.cropdiarymobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.myapps.cropdiarymobile.core.RequestCodes
import com.myapps.cropdiarymobile.ui.navigation.AppNavigation
import com.myapps.cropdiarymobile.ui.theme.CropDiaryAppTheme
import com.myapps.cropdiarymobile.ui.theme.CropDiaryMobileTheme
import com.myapps.cropdiarymobile.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CropDiaryAppTheme {
                AppNavigation()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RequestCodes.LOGIN_WITH_GOOGLE) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            authViewModel.finishSignInWithGoogle(task)
        }
    }
}
@ExperimentalPagerApi
@Preview(showBackground = true, device = Devices.PIXEL_2, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CropDiaryMobileTheme {
        //val window = rememberWindowSize()
        AppNavigation()
    }
}