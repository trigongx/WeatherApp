package com.example.weatherapp.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.App.Companion.WEATHER_KEY
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMapBinding
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.presenter.MapPresenter
import com.example.weatherapp.view.MapView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.ScreenPoint
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : Fragment(),MapView {

    @Inject
    lateinit var presenter:MapPresenter
    private lateinit var binding:FragmentMapBinding
    private val TARGET_LOCATION = Point(42.882004,74.582748)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        initClickers()

        binding.mapview.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
    }

    private fun initClickers() {
        binding.btnCurrentLocation.setOnClickListener {

        }
        binding.btnShowWeather.setOnClickListener {
            presenter.getWeatherDetails(TARGET_LOCATION.latitude,TARGET_LOCATION.longitude)
        }
    }

    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun getLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                } else -> { }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    /*override fun getCurrentLocation() {
        val inputListener = object :InputListener{
            override fun onMapTap(p0: Map, p1: Point) {
                with(binding){
                    val worldPoint = p1
                    Log.e("ololo","Work${p1.latitude}${p1.longitude}")
                    worldPoint?.let {
                        p0.mapObjects.addPlacemark(
                            it,
                            ImageProvider.fromResource(requireContext(),R.drawable.ic_location)
                        )
                    }
                }
            }

            override fun onMapLongTap(p0: Map, p1: Point) {
                Toast.makeText(requireContext(), "ok", Toast.LENGTH_SHORT).show()
            }

        }
        binding.mapview.map.addInputListener(inputListener)
    }*/

    override fun navigateToWeatherFragment(weatherModel: WeatherModel) {
        findNavController().navigate(R.id.weatherFragment, bundleOf(WEATHER_KEY to weatherModel))
    }

    override fun showToast(msg: String) {
        Toast.makeText(requireContext(), "msg", Toast.LENGTH_SHORT).show()
    }
}