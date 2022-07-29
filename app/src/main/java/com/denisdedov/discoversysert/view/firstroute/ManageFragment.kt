package com.denisdedov.discoversysert.view.firstroute

import android.media.MediaPlayer
import android.os.Bundle
import android.os.PowerManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentManageBinding
import com.denisdedov.discoversysert.model.SeekBarHandler
import com.denisdedov.discoversysert.model.routes.Carousel
import com.denisdedov.discoversysert.model.routes.CarouselAdapter
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class ManageFragment : Fragment(), SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, View.OnClickListener {

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

    private var mMediaPlayer: MediaPlayer? = null
    private var mPlayPauseButton: ImageButton? = null
    private var mSeekbar:SeekBar? = null
    private var mTimer: TextView? = null
    private var seekBarHandler: SeekBarHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                relaxResources(true)
            }
        })
    }

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

        mSeekbar = binding.progressbar
        mSeekbar?.setOnSeekBarChangeListener(this)

        mPlayPauseButton = binding.playPauseBtn
        mPlayPauseButton?.setOnClickListener(this)

        mTimer = binding.tvProgress

        return binding.root
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.play_pause_btn) {
            togglePlayback()
        }
    }

    fun togglePlayback() {
        if (mMediaPlayer?.isPlaying == true) {
            pauseAudio()
        } else {
            createMediaPlayerIfNeeded()
            playAudio()
        }
    }

    /**
     * Makes sure the media player exists and has been reset. This will create
     * the media player if needed, or reset the existing media player if one
     * already exists.
     */
    private fun createMediaPlayerIfNeeded() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(activity, R.raw.manage)

            // Make sure the media player will acquire a wake-lock while
            // playing. If we don't do that, the CPU might go to sleep while the
            // song is playing, causing playback to stop.
            mMediaPlayer?.setWakeMode(
                requireActivity().getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK)

            // we want the media player to notify us when it's ready preparing,
            // and when it's done playing:
            mMediaPlayer?.setOnPreparedListener(this)
            mMediaPlayer?.setOnCompletionListener(this)
            mMediaPlayer?.setOnErrorListener(this)
            mMediaPlayer?.setOnSeekCompleteListener(this)

        }
    }

    fun playAudio() {
        mMediaPlayer?.start()
        seekBarHandler = SeekBarHandler(mSeekbar, mMediaPlayer, isViewOn = true, timer = mTimer!!)
        seekBarHandler?.execute()
        val pauseDrawabale = ContextCompat.getDrawable(requireActivity(), android.R.drawable.ic_media_pause)
        mPlayPauseButton?.setImageDrawable(pauseDrawabale)
    }

    fun pauseAudio() {
        seekBarHandler?.cancel(true)
        mMediaPlayer?.pause()
        val playDrawabale = ContextCompat.getDrawable(requireActivity(), android.R.drawable.ic_media_play)
        mPlayPauseButton?.setImageDrawable(playDrawabale)
    }

    /**
     * Releases resources used by the service for playback. This includes the
     * "foreground service" status, the wake locks and possibly the MediaPlayer.
     * @param releaseMediaPlayer Indicates whether the Media Player should also
     * *            be released or not
     */
    private fun relaxResources(releaseMediaPlayer: Boolean) {
        seekBarHandler?.cancel(true)
        seekBarHandler = null
        // stop and release the Media Player, if it's available
        if (releaseMediaPlayer && mMediaPlayer != null) {
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }

    override fun onPause() {
        super.onPause()
        if (mMediaPlayer?.isPlaying == true) {
            pauseAudio()
        }
    }


    override fun onCompletion(mp: MediaPlayer?) {
        relaxResources(true)
        val playDrawabale = ContextCompat.getDrawable(requireActivity(), android.R.drawable.ic_media_play)
        mPlayPauseButton?.setImageDrawable(playDrawabale)
        mSeekbar?.progress = 0
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return false
    }

    override fun onPrepared(mp: MediaPlayer?) {
    }

    override fun onSeekComplete(mp: MediaPlayer?) {
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            val progress = seekBar?.progress
            mMediaPlayer?.seekTo(progress!!)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
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