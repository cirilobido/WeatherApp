package com.androidstudy.weatherapp.presentation.ui.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.material.Divider
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidstudy.weatherapp.domain.weather.WeatherDataModel
import com.plcoding.weatherapp.domain.weather.WeatherType
import com.plcoding.weatherapp.presentation.ui.theme.DividerColor
import com.plcoding.weatherapp.presentation.ui.theme.SubtitlesTextColor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DailyWeatherDisplay(
    dayName: String,
    weatherType: WeatherType,
    maxTemperature: Double,
    minTemperature: Double
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
    ) {
        Text(
            text = dayName,
            modifier = Modifier
                .widthIn(100.dp),
            style = TextStyle(
                fontSize = 20.sp,
                color = SubtitlesTextColor,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Image(
            painter = painterResource(id = weatherType.iconRes),
            contentDescription = weatherType.weatherDesc,
            modifier = Modifier.size(34.dp)
        )
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "${maxTemperature.toInt()}°",
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "${minTemperature.toInt()}°",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = SubtitlesTextColor,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
    }
}