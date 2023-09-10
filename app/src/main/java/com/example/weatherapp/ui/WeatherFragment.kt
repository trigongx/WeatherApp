package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.App.Companion.WEATHER_KEY
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.presenter.WeatherPresenter
import com.example.weatherapp.view.WeatherView
import dagger.hilt.android.AndroidEntryPoint

class WeatherFragment : Fragment(),WeatherView {

    private lateinit var binding:FragmentWeatherBinding
    private val presenter=  WeatherPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        val result = arguments?.getSerializable(WEATHER_KEY) as WeatherModel
        presenter.getData(result)
    }

    override fun showWeather(weatherModel: WeatherModel) {
        with(binding){
            tvCity.text = weatherModel.name
            tvTemp.text = weatherModel.main.temp.toString()
            tvTempMin.text = weatherModel.main.temp_min.toString()
            tvTempMax.text = weatherModel.main.temp_max.toString()
            tvWindSpeed.text = weatherModel.wind.speed.toString() + "м/с"
            tvHumidity.text = weatherModel.main.humidity.toString() + "Па"

        }
    }

}