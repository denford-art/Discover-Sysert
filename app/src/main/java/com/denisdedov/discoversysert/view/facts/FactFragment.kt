package com.denisdedov.discoversysert.view.facts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentFactBinding

class FactFragment : Fragment() {

    lateinit var binding: FragmentFactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFactBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FactFragment()
    }
}