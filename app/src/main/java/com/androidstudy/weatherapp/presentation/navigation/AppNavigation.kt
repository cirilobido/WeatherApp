package com.androidstudy.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidstudy.weatherapp.SplashScreenActivity
import com.androidstudy.weatherapp.MainActivity

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route){
        composable(AppScreens.SplashScreen.route){
            SplashScreenActivity()
        }
        composable(AppScreens.WeatherScreen.route){
            navController.popBackStack()
            MainActivity()
        }
    }
}