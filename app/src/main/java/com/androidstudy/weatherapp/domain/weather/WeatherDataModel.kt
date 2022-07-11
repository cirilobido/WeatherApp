package com.androidstudy.weatherapp.domain.weather

import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime

data class WeatherDataModel(
    val time: LocalDateTime,
    val temperatureCelcius: Double,
    val pressure: Double,
    val windSpeed : Double,
    val humidity: Double,
    val weatherType: WeatherType
)
