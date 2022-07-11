package com.androidstudy.weatherapp.domain.weather

data class WeatherInfo (
    val weatherDataPerDay: Map<Int, List<WeatherDataModel>>,
    val currentWeatherData: WeatherDataModel?
)
