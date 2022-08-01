package com.denisdedov.discoversysert.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.scalebar.scalebar

class CustomMap constructor(mapView: MapView) {
    val mapView: MapView = mapView
    fun CreateCustomMap(style: String, points: List<MarkerForCM>) {
        mapView.scalebar.enabled = false
        mapView.getMapboxMap().loadStyleUri(
            style
        ) {
            for(point in points)
                getPlaceMark(point.coordinate, point.title)
        }
    }
    fun CreateCustomMap(style: String, point: MarkerForCM) {
        mapView.scalebar.enabled = false
        mapView.getMapboxMap().loadStyleUri(
            style
        ) {
            getPlaceMark(point.coordinate, point.title)
        }
    }

    private fun getPlaceMark(point: Point, text: String) {
        drawSimpleBitmap(text)?.let {
            val annotationApi = mapView?.annotations
            val pointAnnotationManager = annotationApi?.createPointAnnotationManager(mapView!!)
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(it)
            pointAnnotationManager?.create(pointAnnotationOptions)
        }
    }

    fun drawSimpleBitmap(name: String): Bitmap {
        val picSize: Int = 120;
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
        paint.setTextSize(30F);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(name, (picSize / 2).toFloat(),
            picSize / 2 - ((paint.descent() + paint.ascent()) / 2), paint);
        return bitmap;
    }
}