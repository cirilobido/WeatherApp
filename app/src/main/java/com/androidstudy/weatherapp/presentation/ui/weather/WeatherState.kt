package com.androidstudy.weatherapp.presentation.ui.weather

import com.androidstudy.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
