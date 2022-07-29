package com.denisdedov.discoversysert.view.firstroute

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentAboutFirstRouteBinding
import com.denisdedov.discoversysert.model.routes.Carousel
import com.denisdedov.discoversysert.model.routes.CarouselAdapter
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class AboutFirstRouteFragment : Fragment() {

    lateinit var binding: FragmentAboutFirstRouteBinding
    private val carouselAdapter: CarouselAdapter = CarouselAdapter()
    private val imgIdList = listOf(
        R.drawable.history_temple,
        R.drawable.history_factory_new,
        R.drawable.history_manor,
    )

    private lateinit var mapView: MapView
    private val factory: Point = Point(56.494141, 60.810552)
    private val manor: Point = Point(56.494913, 60.808632)
    private val manage: Point = Point(56.495156, 60.810196)
    private val hill: Point = Point(56.489174, 60.811757)
    private val dam: Point = Point(56.493897, 60.809109)
    private val temple: Point = Point(56.495807, 60.809504)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutFirstRouteBinding.inflate(inflater, container, false)
        val navController = NavHostFragment.findNavController(this)
        initImg()

        mapView = binding.mapviewAboutHistory

        mapView.map
            .move(
                CameraPosition(
                    factory, 15.0f, 0.0f, 0.0f
                ),
                Animation(Animation.Type.SMOOTH, 0f),
                null)

        val mapOblect = mapView.map.mapObjects.addCollection()

        // History route objects
        getPlaceMark(factory, R.string.factory)
        getPlaceMark(manage, R.string.manage)
        getPlaceMark(manor, R.string.manor)
        getPlaceMark(dam, R.string.dam)
        getPlaceMark(hill, R.string.hill)
        getPlaceMark(temple, R.string.temple)

        return binding.root
    }


    fun getPlaceMark(point: Point, name: Int){
        mapView.map.mapObjects.addCollection().addPlacemark(
            point,
            ImageProvider.fromBitmap(drawSimpleBitmap(getString(name)))
        )
    }


    fun drawSimpleBitmap(name: String): Bitmap {
        val picSize: Int = 90;
        val bitmap: Bitmap = Bitmap.createBitmap(picSize, picSize, Bitmap.Config.ARGB_8888);
        val canvas = Canvas(bitmap);
        // отрисовка плейсмарка
        val paint = Paint();
        paint.setColor(Color.rgb(44, 120, 115));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((picSize / 2).toFloat(), (picSize / 2).toFloat(),
            (picSize / 2).toFloat(), paint);
        // отрисовка текста
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTextSize(22F);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(name, (picSize / 2).toFloat(),
            picSize / 2 - ((paint.descent() + paint.ascent()) / 2), paint);
        return bitmap;
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