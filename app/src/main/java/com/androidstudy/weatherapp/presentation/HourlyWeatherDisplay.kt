package com.androidstudy.weatherapp.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidstudy.weatherapp.domain.weather.WeatherDataModel
import com.plcoding.weatherapp.presentation.ui.theme.SubtitlesTextColor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherDataModel
) {
    val formattedTime = remember(weatherData) {
        if (LocalDateTime.now().hour == weatherData.time.hour) {
            "Now"
        } else {
            weatherData.time.format(
                DateTimeFormatter.ofPattern("HH")
            )
        }
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            style = TextStyle(
                fontSize = 18.sp,
                color = SubtitlesTextColor,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = weatherData.weatherType.weatherDesc,
            modifier = Modifier.size(34.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${weatherData.temperatureCelcius.toInt()}°",
            style = TextStyle(
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            ),
        )
    }
}