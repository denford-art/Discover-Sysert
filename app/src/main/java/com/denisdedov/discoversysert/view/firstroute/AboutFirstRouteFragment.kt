package com.denisdedov.discoversysert.view.firstroute

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.denisdedov.discoversysert.R

class AboutFirstRouteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_first_route, container, false)
        val navController = NavHostFragment.findNavController(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_to_route: Button = view.findViewById(R.id.btn_to_route)
        btn_to_route.setOnClickListener {
            findNavController().navigate(R.id.firstRouteFragment, null)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AboutFirstRouteFragment()
    }
}