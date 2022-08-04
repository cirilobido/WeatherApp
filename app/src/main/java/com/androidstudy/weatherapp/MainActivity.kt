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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidstudy.weatherapp.presentation.*
import com.plcoding.weatherapp.presentation.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var state: WeatherState

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
            state = viewModel.state
            WeatherAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundWhiteColor),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
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
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Bonao",
                                    style = TextStyle(
                                        fontSize = 22.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                )
                                Text(
                                    text = "Dominican Republic",
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
                            thickness = 0.2.dp
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            if (state.isLoading) {
                                CircularProgressIndicator(
                                    color = BlueColor,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(16.dp)
                                )
                            } else if (state.error != null) {
                                Text(
                                    text = state.error!!,
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontSize = 22.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Normal,
                                    ),
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(16.dp)
                                )
                            } else {
                                Box(
                                    /*modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(400.dp)
                                        .background(
                                            brush = Brush.radialGradient(
                                                colors = listOf(
                                                    BlueColor.copy(alpha = 0.3f),
                                                    BlueColor.copy(alpha = 0.2f),
                                                    BlueColor.copy(alpha = 0.1f),
                                                    BackgroundWhiteColor
                                                )
                                            )
                                        )*/
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .verticalScroll(rememberScrollState())
                                            .fillMaxWidth()
                                    ) {
                                        WeatherCard(
                                            state = state,
                                        )
                                        Column(
                                            modifier = Modifier
                                                .background(BackgroundWhiteColor)
                                        ) {
                                            WeatherForecast(state = state, day = 0)
                                            WeatherForecast(state = state, day = 1)
                                            WeatherForecast(state = state, day = 2)
                                            WeatherForecast(state = state, day = 3)
                                            WeatherForecast(state = state, day = 4)
                                            WeatherForecast(state = state, day = 5)
                                            WeatherForecast(state = state, day = 6)
                                            Spacer(modifier = Modifier.height(20.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}