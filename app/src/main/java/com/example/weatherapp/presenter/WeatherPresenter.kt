package com.example.weatherapp.presenter

import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.view.WeatherView

class WeatherPresenter(private val weatherView: WeatherView) {

    fun getData(weatherModel: WeatherModel){
        weatherView.showWeather(weatherModel)
    }

}