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
        Fact(R.drawable.history_temple, R.string.emperor_fact, R.id.empererFactFragment),
        Fact(R.drawable.park_lake, R.string.park_in_life_fact, R.id.parkFactFragment),
        Fact(R.drawable.gidromash, R.string.gibromash_fact, R.id.gidromashFactFragment),
        Fact(R.drawable.history_dam_render, R.string.dam_skillet_fact, R.id.damFactFragment),
        Fact(R.drawable.history_factory_new, R.string.eiffel_tower_fact, R.id.eiffelFactFragment),
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
        binding.tvTitle1.text = getString(factRandom.title)
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