package com.androidstudy.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidstudy.weatherapp.R
import com.plcoding.weatherapp.presentation.ui.theme.*
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    state: WeatherState,
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Column() {
            Card(
                elevation = 0.dp,
                backgroundColor = LightBlueColor,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(200.dp)
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .heightIn(200.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)

                ) {
                    Image(
                        painter = painterResource(id = data.weatherType.iconRes),
                        contentDescription = data.weatherType.weatherDesc,
                        colorFilter = ColorFilter.tint(LightBlueTintColor),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 100.dp, y = -10.dp)
                            .width(150.dp),
                    )
                    Image(
                        painter = painterResource(id = data.weatherType.iconRes),
                        contentDescription = data.weatherType.weatherDesc,
                        colorFilter = ColorFilter.tint(LightBlueTintColor),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset(x = -80.dp, y = 20.dp)
                            .width(150.dp),
                    )
                }
                Column(
                    modifier = Modifier
                        .heightIn(200.dp)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "${data.temperatureCelsius}°C",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 48.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                    Text(
                        text = "Today ${
                            data.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                        }",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.Black.copy(alpha = 0.3f),
                            fontWeight = FontWeight.Normal,
                        ),
                    )
                    Spacer(Modifier.height(16.dp))
                    Card(
                        elevation = 0.dp,
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 8.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = data.weatherType.iconRes),
                                contentDescription = data.weatherType.weatherDesc,
                                modifier = Modifier.width(32.dp)
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text(
                                text = data.weatherType.weatherDesc,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                ),
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherDataDisplay(
                    value = data.humidity.toInt(),
                    title = "Humidity",
                    unit = "%",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    iconTint = LightTurquoiseColor
                )
                WeatherDataDisplay(
                    value = data.windSpeed.toInt(),
                    title = "Wind",
                    unit = "km/h",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    iconTint = BlueColor
                )
                WeatherDataDisplay(
                    value = data.pressure.toInt(),
                    title = "Pressure",
                    unit = "hpa",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                    iconTint = OrangeColor
                )
            }
        }
    }
}