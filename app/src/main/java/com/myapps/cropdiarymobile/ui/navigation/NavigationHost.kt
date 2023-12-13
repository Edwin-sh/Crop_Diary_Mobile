package com.myapps.cropdiarymobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapps.cropdiarymobile.ui.navigation.Destinations.MainScreen
import com.myapps.cropdiarymobile.ui.navigation.Destinations.OnBoardingScreen
import com.myapps.cropdiarymobile.ui.navigation.Destinations.SignInScreen
import com.myapps.cropdiarymobile.ui.navigation.Destinations.SplashScreen
import com.myapps.cropdiarymobile.ui.screens.main.MainScreen
import com.myapps.cropdiarymobile.ui.screens.splashScreen.SplashViewScreen
import com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.SignInScreen
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.OnBoardingScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController found")
}

@ExperimentalPagerApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = SplashScreen.route) {
            composable(SplashScreen.route) {
                SplashViewScreen()
            }
            composable(OnBoardingScreen.route) {
                OnBoardingScreen()
            }
            composable(SignInScreen.route) {
                SignInScreen()
            }
            composable(MainScreen.route) {
                MainScreen()
            }
        }
    }

}
