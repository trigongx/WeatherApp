package com.example.weatherapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun getCurrentWeather(@Query("lon") lon:Double,
                          @Query("lat") lat:Double,
                          @Query("units") units:String = "metric",
                          @Query("appid") appid:String = "36cbf21e77603d1848c9293fd5e5ba31"
                          ): Call<WeatherModel>

}