package com.denisdedov.discoversysert.view.firstroute

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

class AboutFirstRouteFragment : Fragment() {

    lateinit var binding: FragmentAboutFirstRouteBinding
    private val carouselAdapter: CarouselAdapter = CarouselAdapter()
    private val imgIdList = listOf(
        R.drawable.history_temple,
        R.drawable.history_factory_new,
        R.drawable.history_manor,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutFirstRouteBinding.inflate(inflater, container, false)

        initImg()

        return binding.root
        val navController = NavHostFragment.findNavController(this)
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