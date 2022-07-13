package com.androidstudy.weatherapp.data.repository

import com.androidstudy.weatherapp.data.mappers.toWeatherInfo
import com.androidstudy.weatherapp.data.remote.WeatherAPI
import com.androidstudy.weatherapp.domain.WeatherRepository
import com.androidstudy.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherAPI) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat = lat, long = long).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}