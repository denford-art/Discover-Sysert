package com.denisdedov.discoversysert.view.facts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denisdedov.discoversysert.R

class FactFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fact, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FactFragment()
    }
}