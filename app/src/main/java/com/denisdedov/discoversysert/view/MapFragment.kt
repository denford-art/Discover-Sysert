package com.denisdedov.discoversysert.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentMapBinding
import com.denisdedov.discoversysert.model.CustomMap
import com.denisdedov.discoversysert.model.MarkerForCM
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView


class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding
    private lateinit var customMap: CustomMap

    private lateinit var mapView: MapView

    val points = listOf<MarkerForCM>(
        MarkerForCM(Point.fromLngLat(60.810552,56.494141), "Завод"),
        MarkerForCM(Point.fromLngLat(60.808632, 56.494913), "Усадьба"),
        MarkerForCM(Point.fromLngLat(60.810196, 56.495156), "Управление"),
        MarkerForCM(Point.fromLngLat(60.811757, 56.489174), "Гора"),
        MarkerForCM(Point.fromLngLat(60.809109, 56.493897), "Плотина"),
        MarkerForCM(Point.fromLngLat(60.809504, 56.495807), "Храм")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        mapView = binding.mapView
        customMap = CustomMap(mapView)
        customMap.CreateCustomMap(getString(R.string.mapStyle), points)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()
    }
}