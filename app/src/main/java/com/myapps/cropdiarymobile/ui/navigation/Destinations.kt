package com.myapps.cropdiarymobile.ui.navigation

sealed class Destinations(
    val route: String
) {
    object SplashScreen: Destinations("splashScreen")
    object OnBoardingScreen: Destinations("onBoardingScreen")
    object SignInScreen: Destinations("sinInScreen")
    object MainScreen: Destinations("mainScreen")
}