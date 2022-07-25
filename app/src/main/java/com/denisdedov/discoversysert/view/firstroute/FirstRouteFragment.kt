package com.denisdedov.discoversysert.view.firstroute

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denisdedov.discoversysert.R

class FirstRouteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_route, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstRouteFragment()
    }
}