package com.myapps.cropdiarymobile.ui.screens.splashScreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.navigation.Destinations
import com.myapps.cropdiarymobile.ui.navigation.LocalNavController
import com.myapps.cropdiarymobile.ui.screens.splashScreen.components.BackGround
import com.myapps.cropdiarymobile.ui.screens.splashScreen.components.ConstraintLayoutSplashScreen
import com.myapps.cropdiarymobile.ui.screens.splashScreen.components.Logo
import com.myapps.cropdiarymobile.ui.screens.splashScreen.components.ProgressIndicator
import com.myapps.cropdiarymobile.ui.screens.splashScreen.components.Slogan
import com.myapps.cropdiarymobile.ui.theme.SecondAppTypography
import com.myapps.cropdiarymobile.ui.viewmodel.OnBoardingViewModel

@Composable
fun SplashViewScreen(
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val grid = getWindowInformation().windowGrid
    val state = onBoardingViewModel.state
    val nextScreen = when {
        !state.isLoading -> {
            if (state.isComplete) Destinations.SignInScreen.route else Destinations.OnBoardingScreen.route
        }

        else -> {
            ""
        }
    }
    if (nextScreen != "") {
        navController.popBackStack()
        navController.navigate(nextScreen)
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
                    .layoutId(LayoutId.logo)
            )
            Slogan(
                orientation = windowOrientation,
                grid = grid,
                text = stringResource(id = R.string.slogan),
                modifier = Modifier
                    .layoutId(LayoutId.slogan),
                textStyle = SecondAppTypography.titleLarge
            )
            if (state.isLoading) {
                ProgressIndicator(
                    orientation = windowOrientation,
                    grid = grid,
                    modifier = Modifier
                        .layoutId(LayoutId.progressIndicator)
                )
            }
        }

    }
}


