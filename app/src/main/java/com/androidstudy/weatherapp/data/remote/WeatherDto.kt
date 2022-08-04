package com.androidstudy.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherDataDto: WeatherDataDto
)
