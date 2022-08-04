package com.androidstudy.weatherapp.presentation

import android.location.Address

data class AddressState(
    val addressInfo: Address? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
