package com.androidstudy.weatherapp.domain

import com.androidstudy.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}