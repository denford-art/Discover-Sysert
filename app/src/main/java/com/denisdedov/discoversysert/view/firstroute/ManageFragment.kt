package com.denisdedov.discoversysert.view.firstroute

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentManageBinding
import com.denisdedov.discoversysert.model.routes.Carousel
import com.denisdedov.discoversysert.model.routes.CarouselAdapter
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class ManageFragment : Fragment() {

    lateinit var binding: FragmentManageBinding
    private val carouselAdapter: CarouselAdapter = CarouselAdapter()
    private val imgIdList = listOf(
        R.drawable.history_manage,
        R.drawable.history_manage,
        R.drawable.history_manage,
        R.drawable.history_manage,
        R.drawable.history_manage,
    )

    private lateinit var mapView: MapView
    private val startPoint: Point = Point(56.496373, 60.813429)

    private lateinit var mediaPLayer: MediaPlayer
    private var totalTime: Int = 0
    private lateinit var positionBar: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBinding.inflate(inflater, container, false)
        initImg()

        mapView = binding.mapviewManage
        mapView.map
            .move(
                CameraPosition(
                    startPoint, 15.0f, 0.0f, 0.0f
                ),
                Animation(Animation.Type.SMOOTH, 0f),
                null)
        val mapOblect = mapView.map.mapObjects.addCollection()
        val placeMark = mapOblect.addPlacemark(
            startPoint,
            ImageProvider.fromResource(activity, R.drawable.route_start)
        )

        mediaPLayer = MediaPlayer.create(activity, R.raw.manage)
        mediaPLayer.isLooping = true
        mediaPLayer.setVolume(0.5f, 0.5f)
        totalTime = mediaPLayer.duration


        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mediaPLayer.seekTo(progress)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Thread
        Thread(Runnable {
            while (mediaPLayer != null) {
                try {
                    var msg = Message()
                    msg.what = mediaPLayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()

        binding.ibPlay.setOnClickListener{playBtnClick()}

        return binding.root
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            // Update positionBar
            positionBar.progress = currentPosition
        }
    }

    fun playBtnClick() {

        if (mediaPLayer.isPlaying) {
            // Stop
            mediaPLayer.pause()
            binding.ibPlay.setBackgroundResource(R.drawable.ic_play)

        } else {
            // Start
            mediaPLayer.start()
            binding.ibPlay.setBackgroundResource(R.drawable.ic_pause)
        }
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

    private fun initImg() {
        binding.apply {
            rvImageManage.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.HORIZONTAL, false)
            rvImageManage.adapter = carouselAdapter
            for(img in imgIdList){
                val image = Carousel(img)
                carouselAdapter.addCarousel(image)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DamFragment()
    }
}