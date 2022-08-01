package com.androidstudy.weatherapp.di

import com.androidstudy.weatherapp.data.location.DefaultLocationTracker
import com.androidstudy.weatherapp.data.repository.WeatherRepositoryImpl
import com.androidstudy.weatherapp.domain.WeatherRepository
import com.androidstudy.weatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}