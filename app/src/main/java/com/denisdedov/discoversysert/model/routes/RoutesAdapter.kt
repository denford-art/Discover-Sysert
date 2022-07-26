package com.denisdedov.discoversysert.model.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.RouteItemBinding

class RoutesAdapter: RecyclerView.Adapter<RoutesAdapter.RouteHolder>() {
    var routesList = ArrayList<Route>()
    class RouteHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = RouteItemBinding.bind(item)
        fun bind(route: Route) {
            binding.ivRoute.setImageResource(route.imageId)
            binding.tvRouteTitle.text = route.title
            binding.tvDistance.text = route.dist
            binding.tvTime.text = route.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.route_item, parent, false)
        return RouteHolder(view)
    }

    override fun onBindViewHolder(holder: RouteHolder, position: Int) {
        holder.bind(routesList[position])
    }

    override fun getItemCount(): Int {
        return routesList.size
    }

    fun addRoute(route: Route) {
        routesList.add(route)
        notifyDataSetChanged()
    }

}