package com.example.weatherapp.view

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherModel

interface WeatherView {

    fun showWeather(weatherModel: WeatherModel)
}