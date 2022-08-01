package com.denisdedov.discoversysert.view.facts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentFactBinding

class ParkFactFragment() : Fragment() {

    lateinit var binding: FragmentFactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFactBinding.inflate(inflater, container, false)

        binding.tvFactText.text = getString(R.string.park_in_life_fact)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ParkFactFragment()
    }
}