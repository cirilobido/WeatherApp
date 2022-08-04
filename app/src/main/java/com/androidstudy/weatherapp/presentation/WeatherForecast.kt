package com.androidstudy.weatherapp.presentation

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.presentation.ui.theme.DividerColor
import java.time.LocalDateTime

@Composable
fun WeatherForecast(
    state: WeatherState,
    day: Int
) {
    state.weatherInfo?.weatherDataPerDay?.get(day)
        ?.filter { filterData -> filterData.time >= LocalDateTime.now().minusHours(1) }
        ?.toList()
        ?.let { data ->
            val dayName = when (day) {
                0 -> "Today"
                1 -> "Tomorrow"
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = dayName,
                    style = TextStyle(
                        fontSize = 22.sp,
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
                        .padding(16.dp),
                    content = {
                        items(data) { weatherData ->
                            HourlyWeatherDisplay(weatherData = weatherData)
                        }
                    })
            }
        }
}