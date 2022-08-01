package com.denisdedov.discoversysert.view.firstroute

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentAboutFirstRouteBinding
import com.denisdedov.discoversysert.model.CustomMap
import com.denisdedov.discoversysert.model.MarkerForCM
import com.denisdedov.discoversysert.model.routes.Carousel
import com.denisdedov.discoversysert.model.routes.CarouselAdapter
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView


class AboutFirstRouteFragment : Fragment() {

    lateinit var binding: FragmentAboutFirstRouteBinding
    private lateinit var customMap: CustomMap

    private val carouselAdapter: CarouselAdapter = CarouselAdapter()
    private val imgIdList = listOf(
        R.drawable.history_temple,
        R.drawable.history_factory_new,
        R.drawable.history_manor,
    )

    private lateinit var mapView: MapView
    val points = listOf<MarkerForCM>(
        MarkerForCM(Point.fromLngLat(60.810552,56.494141), "Завод"),
        MarkerForCM(Point.fromLngLat(60.808632, 56.494913), "Завод"),
        MarkerForCM(Point.fromLngLat(60.810196, 56.495156), "Завод"),
        MarkerForCM(Point.fromLngLat(60.811757, 56.489174), "Завод"),
        MarkerForCM(Point.fromLngLat(60.809109, 56.493897), "Завод"),
        MarkerForCM(Point.fromLngLat(60.809504, 56.495807), "Завод")
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutFirstRouteBinding.inflate(inflater, container, false)
        val navController = NavHostFragment.findNavController(this)
        initImg()

        mapView = binding.mapviewAboutHistory
        customMap = CustomMap(mapView)
        customMap.CreateCustomMap(getString(R.string.mapStyle), points)

        mapView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> binding.myview.requestDisallowInterceptTouchEvent(true)
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> binding.myview.requestDisallowInterceptTouchEvent(
                    false
                )
            }
            mapView.onTouchEvent(event)
        }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_to_route: Button = view.findViewById(R.id.btn_to_route)
        btn_to_route.setOnClickListener {
            findNavController().navigate(R.id.firstRouteFragment, null)
        }
    }

    private fun initImg() {
        binding.apply {
            rvImageAbouteFirst.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rvImageAbouteFirst.adapter = carouselAdapter
            for(img in imgIdList){
                val image = Carousel(img)
                carouselAdapter.addCarousel(image)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AboutFirstRouteFragment()
    }
}