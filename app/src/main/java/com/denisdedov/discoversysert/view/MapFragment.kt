package com.denisdedov.discoversysert.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding

    private lateinit var mapView: MapView
    private val apiKey: String = "92e9100b-adc2-4276-95d6-c5756940503d"
    private val startPoint: Point = Point(56.496373, 60.813429)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MapKitFactory.setApiKey(apiKey)
        MapKitFactory.initialize(activity)
        binding = FragmentMapBinding.inflate(inflater, container, false)

        mapView = binding.mapViewMain

        mapView.map
            .move(
                CameraPosition(
                    startPoint, 11.0f, 0.0f, 0.0f
                ),
                Animation(Animation.Type.SMOOTH, 0f),
                null)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()
    }
}