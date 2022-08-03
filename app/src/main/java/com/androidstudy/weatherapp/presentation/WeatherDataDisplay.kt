package com.androidstudy.weatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.presentation.ui.theme.SubtitlesTextColor

@Composable
fun WeatherDataDisplay(
    value: Int,
    title: String,
    unit: String,
    icon: ImageVector,
    iconTint: Color = Color.Black
) {
    Row(
    ) {
        Icon(
            imageVector = icon,
            contentDescription = unit,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(
        ) {
            Text(
                text = "$value$unit",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                ),
            )
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = SubtitlesTextColor,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
    }
}