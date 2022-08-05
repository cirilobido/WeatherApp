package com.androidstudy.weatherapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.androidstudy.weatherapp.presentation.navigation.AppScreens
import com.androidstudy.weatherapp.presentation.ui.weather.AddressState
import com.plcoding.weatherapp.presentation.ui.theme.BackgroundWhiteColor
import com.plcoding.weatherapp.presentation.ui.theme.RedColor
import com.plcoding.weatherapp.presentation.ui.theme.SubtitlesTextColor
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundWhiteColor),
                ) {
                    Loading()
                }
                val intent = Intent(this, MainActivity::class.java)
                LaunchedEffect(key1 = true, ){
                    delay(2700)
                    startActivity(intent)
                    finishAfterTransition()
//                    navController.popBackStack()
//                    navController.navigate(AppScreens.WeatherScreen.route)
                }
            }
        }
    }
}

@Composable
fun Loading() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Text(
            text = stringResource(R.string.app_desc),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = SubtitlesTextColor,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
        )
    }
}