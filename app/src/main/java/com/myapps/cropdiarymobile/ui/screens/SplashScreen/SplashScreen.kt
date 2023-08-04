package com.myapps.cropdiarymobile.ui.screens.splashScreen

import ProgressIndicator
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.components.getWindowGrid
import com.myapps.cropdiarymobile.ui.navigation.Destinations
import com.myapps.cropdiarymobile.ui.theme.CropDiaryAppTheme
import com.myapps.cropdiarymobile.ui.theme.SecondAppTypography
import kotlinx.coroutines.delay

@Composable
fun SplashViewScreen(navController: NavHostController) {
    val grid = getWindowGrid()
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
        val constraints = Constraints(windowOrientation)
        ConstraintLayoutSplashScreen(
            windowOrientation = windowOrientation,
            constraints = constraints,
            modifier = Modifier
                .fillMaxSize()
                .padding(grid.margin)
        ) {
            Logo(
                orientation = windowOrientation,
                grid = grid,
                modifier = Modifier
                    .layoutId("logo")
            )
            Slogan(
                orientation = windowOrientation,
                grid = grid,
                text = stringResource(id = R.string.slogan),
                modifier = Modifier
                    .layoutId("slogan"),
                textStyle = SecondAppTypography.titleLarge
            )
            ProgressIndicator(
                orientation = windowOrientation,
                grid = grid,
                modifier = Modifier
                    .layoutId("progressIndicator")
            )
        }

    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
private fun SplashScreenPreview() {
    CropDiaryAppTheme {
        SplashViewScreen(navController = rememberNavController())
    }
}


