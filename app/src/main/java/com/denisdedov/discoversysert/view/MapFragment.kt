package com.denisdedov.discoversysert.view

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentMapBinding
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager


class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding

    private lateinit var mapView: MapView
//    private val factory: Point = Point(56.494141, 60.810552)
//    private val manor: Point = Point(56.494913, 60.808632)
//    private val manage: Point = Point(56.495156, 60.810196)
//    private val hill: Point = Point(56.489174, 60.811757)
//    private val dam: Point = Point(56.493897, 60.809109)
//    private val temple: Point = Point(56.495807, 60.809504)
//    private val waterKey: Point = Point(56.494141, 60.810552)
//    private val enter: Point = Point(56.494141, 60.810552)
//    private val bridge: Point = Point(56.494141, 60.810552)
//    private val bobr: Point = Point(56.494141, 60.810552)
//    private val cape: Point = Point(56.494141, 60.810552)
//    private val forest: Point = Point(56.494141, 60.810552)
//    private val poloz: Point = Point(56.494141, 60.810552)
//    private val mirror: Point = Point(56.494141, 60.810552)
//    private val lake: Point = Point(56.494141, 60.810552)
//    private val stone: Point = Point(56.494141, 60.810552)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        mapView = binding.mapView
        mapView?.getMapboxMap()?.loadStyleUri(
            Style.MAPBOX_STREETS,
            object : Style.OnStyleLoaded {
                override fun onStyleLoaded(style: Style) {
                    addAnnotationToMap()
                }
            }
        )


//        mapView = binding.mapViewMain
//
//        mapView.map
//            .move(
//                CameraPosition(
//                    factory, 15.0f, 0.0f, 0.0f
//                ),
//                Animation(Animation.Type.SMOOTH, 0f),
//                null)
//
//        val mapOblect = mapView.map.mapObjects.addCollection()
//
//        // History route objects
//        getPlaceMark(factory, R.string.factory)
//        getPlaceMark(manage, R.string.manage)
//        getPlaceMark(manor, R.string.manor)
//        getPlaceMark(dam, R.string.dam)
//        getPlaceMark(hill, R.string.hill)
//        getPlaceMark(temple, R.string.temple)
//
//        // Park route objects
//        getPlaceMark(waterKey, R.string.water_key)
//        getPlaceMark(enter, R.string.enter)
//        getPlaceMark(bridge, R.string.bridge)
//        getPlaceMark(cape, R.string.cape)
//        getPlaceMark(bobr, R.string.bobr)
//        getPlaceMark(forest, R.string.forest)
//        getPlaceMark(poloz, R.string.poloz)
//        getPlaceMark(mirror, R.string.mirror)
//        getPlaceMark(lake, R.string.lake)
//        getPlaceMark(stone, R.string.stone)


        return binding.root
    }

//    fun getPlaceMark(point: Point, name: Int){
//        mapView.map.mapObjects.addCollection().addPlacemark(
//            point,
//            ImageProvider.fromBitmap(drawSimpleBitmap(getString(name)))
//        )
//    }

    private fun addAnnotationToMap() {
// Create an instance of the Annotation API and get the PointAnnotationManager.
        drawSimpleBitmap("Завод")?.let {
            val annotationApi = mapView?.annotations
            val pointAnnotationManager = annotationApi?.createPointAnnotationManager(mapView!!)
// Set options for the resulting symbol layer.
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
// Define a geographic coordinate.
                .withPoint(Point.fromLngLat(60.810552, 56.494141))
// Specify the bitmap you assigned to the point annotation
// The bitmap will be added to map style automatically.
                .withIconImage(it)
// Add the resulting pointAnnotation to the map.
            pointAnnotationManager?.create(pointAnnotationOptions)
        }
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

//    override fun onStop() {
//        mapView.onStop()
//        MapKitFactory.getInstance().onStop()
//        super.onStop()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        MapKitFactory.getInstance().onStart()
//        mapView.onStart()
//    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()
    }
}