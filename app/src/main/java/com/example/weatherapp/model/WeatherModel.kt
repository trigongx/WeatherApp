package com.example.weatherapp.model

import java.io.Serializable

data class WeatherModel(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
):Serializable

data class Clouds(
    val all: Int
)
data class Coord(
    val lat: Double,
    val lon: Double
)

data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)


