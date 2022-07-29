package com.denisdedov.discoversysert.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentRoutesBinding
import com.denisdedov.discoversysert.model.routes.*


class RoutesFragment : Fragment() {


    lateinit var binding: FragmentRoutesBinding

    val facts = mutableListOf(
        Fact(R.drawable.history_temple, "Занимательный факт 1", R.id.templeFragment),
        Fact(R.drawable.park_lake, "Занимательный факт 2", R.id.templeFragment),
        Fact(R.drawable.gidromash, "Занимательный факт 3", R.id.hillFragment),
        Fact(R.drawable.history_dam_render, "Занимательный факт 4", R.id.damFragment),
        Fact(R.drawable.history_factory_new, "Занимательный факт 5", R.id.factoryFragment),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRoutesBinding.inflate(inflater, container, false)
        binding.clFirstRoute.setOnClickListener {
            findNavController().navigate(R.id.aboutFirstRouteFragment, null)
        }

        val factRandom = facts.shuffled().first()
        binding.tvTitle1.text = factRandom.title
        binding.ivFactTitle1.setImageResource(factRandom.imageId)
        binding.clFact1.setOnClickListener {
            findNavController().navigate(factRandom.fragment, null)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = RoutesFragment()
    }
}