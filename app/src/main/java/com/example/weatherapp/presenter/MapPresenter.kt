package com.example.weatherapp.presenter

import com.example.weatherapp.model.WeatherApi
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.view.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MapPresenter @Inject constructor(private val weatherApi: WeatherApi) {

    lateinit var view:MapView

    fun getWeatherDetails(lat:Double,lon:Double){
        weatherApi.getCurrentWeather(lat,lon).enqueue(object : Callback<WeatherModel>{
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                response.body()?.let {
                    view.navigateToWeatherFragment(it)
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                view.showToast(t.message.toString())
            }

        })

    }


    fun attachView(mapView: MapView){
        this.view = mapView
    }


}