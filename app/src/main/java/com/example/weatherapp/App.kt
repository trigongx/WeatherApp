package com.example.weatherapp

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(MAP_KEY);
    }

    companion object{
        const val MAP_KEY = "129ac4b4-e6b4-4e73-b47e-d1cc6606151f"
        const val WEATHER_KEY = "weather.key"
    }

}