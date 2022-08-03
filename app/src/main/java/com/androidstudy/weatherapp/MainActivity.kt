package com.androidstudy.weatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidstudy.weatherapp.presentation.WeatherCard
import com.androidstudy.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        setContent {
            WeatherAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundWhiteColor),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(BlueColor.copy(alpha = 0.2f))
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_location_marker),
                                contentDescription = "Location Marker",
                                tint = BlueColor,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(30.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                        ) {
                            Text(
                                text = "Dominican Republic",
                                style = TextStyle(
                                    fontSize = 22.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                ),
                            )
                            Text(
                                text = "Bonao",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = SubtitlesTextColor,
                                    fontWeight = FontWeight.SemiBold,
                                ),
                            )
                        }
                    }
                    Divider(
                        color = DividerColor
                            .copy(
                                alpha = 0.5f
                            ),
                        thickness = 0.2.dp)
                    WeatherCard(
                        state = viewModel.state,
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Today",
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
                        thickness = 0.2.dp)
                }
            }
        }
    }
}