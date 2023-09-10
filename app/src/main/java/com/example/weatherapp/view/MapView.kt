package com.example.weatherapp.view

import com.example.weatherapp.model.WeatherModel

interface MapView {

    fun getLocationPermission()
    fun navigateToWeatherFragment(weatherModel: WeatherModel)
    fun showToast(msg:String)

}