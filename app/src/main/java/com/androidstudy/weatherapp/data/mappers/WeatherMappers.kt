package com.androidstudy.weatherapp.data.mappers

import com.androidstudy.weatherapp.data.remote.WeatherDataDto
import com.androidstudy.weatherapp.data.remote.WeatherDto
import com.androidstudy.weatherapp.domain.weather.WeatherDataModel
import com.androidstudy.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexWeatherData(
    val index: Int,
    val data: WeatherDataModel
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherDataModel>>{
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexWeatherData(
            index = index,
            data = WeatherDataModel(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo{
    val weatherDataMap = weatherDataDto.toWeatherDataMap()
    val currentTime = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (currentTime.minute < 30) currentTime.hour else currentTime.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}