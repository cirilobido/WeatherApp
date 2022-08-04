package com.androidstudy.weatherapp.presentation.ui.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidstudy.weatherapp.R
import com.plcoding.weatherapp.domain.weather.WeatherType
import com.plcoding.weatherapp.presentation.ui.theme.DividerColor
import java.time.LocalDateTime

@Composable
fun WeatherForecast(
    state: WeatherState,
    day: Int
) {
    val weatherDataList = state.weatherInfo?.weatherDataPerDay

    val dayName = when (day) {
        0 -> stringResource(R.string.todays_forecast)
        1 -> stringResource(R.string.tomorrow)
        else -> {
            LocalDateTime
                .now()
                .plusDays(day.toLong())
                .dayOfWeek
                .toString()
                .lowercase()
                .replaceFirstChar {
                    it.uppercase()
                }
        }
    }

    if (day >= 1) {
        val weatherTypeList = ArrayList<Int>()
        var maxTemperature = 0.0
        var minTemperature = 60.0
        weatherDataList?.get(day)
            ?.map {
                weatherTypeList.add(it.weatherType.weatherCode)
                maxTemperature = if (maxTemperature > it.temperatureCelsius){
                    maxTemperature
                } else {
                    it.temperatureCelsius
                }
                minTemperature = if (minTemperature < it.temperatureCelsius){
                    minTemperature
                } else {
                    it.temperatureCelsius
                }
            }
            ?.toList()
        val weatherAverageCode = weatherTypeList
            .groupingBy { it }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Divider(
                color = DividerColor
                    .copy(
                        alpha = 0.5f
                    ),
                thickness = 0.2.dp
            )
            DailyWeatherDisplay(
                dayName = dayName,
                weatherType = WeatherType.fromWMO(weatherAverageCode ?: 0),
                maxTemperature = maxTemperature,
                minTemperature = minTemperature
            )
        }
    } else {
        weatherDataList?.get(day)
            ?.filter { filterData ->
                filterData.time >= LocalDateTime.now().minusHours(1)
            }
            ?.toList()
            ?.let { data ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = dayName,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Divider(
                        color = DividerColor
                            .copy(
                                alpha = 0.5f
                            ),
                        thickness = 0.2.dp
                    )
                    LazyRow(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        content = {
                            items(data) { weatherData ->
                                HourlyWeatherDisplay(weatherData = weatherData)
                            }
                        }
                    )
                }
            }
    }
}