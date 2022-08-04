package com.androidstudy.weatherapp.presentation.navigation

sealed class AppScreens (val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object WeatherScreen: AppScreens("weather_screen")
}