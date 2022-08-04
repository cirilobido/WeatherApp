package com.androidstudy.weatherapp.presentation.ui.weather

import android.location.Address

data class AddressState(
    val addressInfo: Address? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
