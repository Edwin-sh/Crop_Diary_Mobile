package com.myapps.cropdiarymobile.ui.screens.splashScreen

import ProgressIndicator
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.navigation.Destinations
import kotlinx.coroutines.delay

@Composable
fun SplashViewScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack()
        navController.navigate(Destinations.OnBoardingScreen.route)
    }
    val windowOrientation =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) WindowOrientation.Portrait else WindowOrientation.Landscape
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackGround(
            painter = painterResource(id = R.drawable.bkg_splash_view),
            modifier = Modifier.fillMaxSize()
        )
        ConstraintLayoutSplashScreen(windowOrientation = windowOrientation) {
            Logo(
                orientation = windowOrientation,
                modifier = Modifier
                    .fillMaxWidth(0.833f)
                    .layoutId("logo")
            )
            Slogan(
                orientation = windowOrientation,
                modifier = Modifier
                    .layoutId("slogan")
            )
            ProgressIndicator(
                orientation = windowOrientation,
                size = maxWidth * 0.15f
            )
        }

    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashViewScreen(navController = rememberNavController())
}


