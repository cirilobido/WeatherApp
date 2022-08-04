package com.androidstudy.weatherapp.presentation

import android.app.Application
import android.location.Address
import android.location.Geocoder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidstudy.weatherapp.domain.WeatherRepository
import com.androidstudy.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
    private val context: Application
) : ViewModel() {
    var weatherState by mutableStateOf(WeatherState())
        private set
    var addressState by mutableStateOf(AddressState())
        private set

    fun getLocationAddress() {
        viewModelScope.launch {
            addressState = addressState.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                try {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    withContext(Dispatchers.IO) {
                        geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        ).also { address ->
                            addressState = addressState.copy(
                                isLoading = false,
                                addressInfo = address[0],
                                error = null
                            )
                        }
                    }
                } catch (e: Exception){
                    addressState = addressState.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun loadWeatherInfo() {
        viewModelScope.launch {
            weatherState = weatherState.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        weatherState = weatherState.copy(
                            isLoading = false,
                            weatherInfo = result.data
                        )
                    }
                    is Resource.Error -> {
                        weatherState = weatherState.copy(
                            isLoading = false,
                            weatherInfo = null,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                weatherState = weatherState.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location."
                )
            }
        }
    }
}