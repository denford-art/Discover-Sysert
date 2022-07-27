package com.denisdedov.discoversysert.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentRoutesBinding
import com.denisdedov.discoversysert.model.routes.Fact
import com.denisdedov.discoversysert.model.routes.FactsAdapter

class RoutesFragment : Fragment() {

    lateinit var binding: FragmentRoutesBinding
    private val factAdapter: FactsAdapter = FactsAdapter()
    private val imgIdList = listOf(
        R.drawable.history_temple,
        R.drawable.park_lake,
        R.drawable.gidromash,
        R.drawable.history_temple,
        R.drawable.history_temple,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRoutesBinding.inflate(inflater, container, false)
        initFacts()

        binding.clFirstRoute.setOnClickListener {
            findNavController().navigate(R.id.aboutFirstRouteFragment, null)
        }

        return binding.root
    }

    private fun initFacts() {
        binding.apply {
            rvFacts.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rvFacts.adapter = factAdapter
            for(img in imgIdList){
                val fact = Fact(img, "Занимательный факт")
                factAdapter.addFact(fact)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RoutesFragment()
    }
}