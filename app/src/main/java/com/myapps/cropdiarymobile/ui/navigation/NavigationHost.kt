package com.myapps.cropdiarymobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapps.cropdiarymobile.core.WindowSize
import com.myapps.cropdiarymobile.ui.navigation.Destinations.*
import com.myapps.cropdiarymobile.ui.screens.onBoarding.OnBoardingScreen
import com.myapps.cropdiarymobile.ui.screens.splashScreen.SplashViewScreen

@ExperimentalPagerApi
@Composable
fun AppNavigation(windowSize: WindowSize){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen.route){
        composable(SplashScreen.route){
            SplashViewScreen(navController)
        }
        composable(OnBoardingScreen.route){
            OnBoardingScreen()
        }
    }
}