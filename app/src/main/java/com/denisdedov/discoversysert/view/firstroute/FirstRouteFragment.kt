package com.denisdedov.discoversysert.view.firstroute

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FragmentFirstRouteBinding

class FirstRouteFragment : Fragment() {

    lateinit var binding: FragmentFirstRouteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openObject(binding.clTemple, R.id.templeFragment)
        openObject(binding.clFactory, R.id.factoryFragment)
        openObject(binding.clDam, R.id.damFragment)
        openObject(binding.clHill, R.id.hillFragment)
        openObject(binding.clManor, R.id.manorFragment)
        openObject(binding.clManage, R.id.manageFragment)
    }

    private fun openObject(click: ConstraintLayout, fragment: Int) {
        click.setOnClickListener {
            findNavController().navigate(fragment, null)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstRouteFragment()
    }
}