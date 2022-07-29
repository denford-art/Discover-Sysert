package com.denisdedov.discoversysert.model.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.CarouselItemBinding

class CarouselAdapter(): RecyclerView.Adapter<CarouselAdapter.ImageHolder>() {

    var imageList = ArrayList<Carousel>()
    class ImageHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = CarouselItemBinding.bind(item)
        fun bind(carousel: Carousel) {
            binding.ivImage.setImageResource(carousel.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun addCarousel(carousel: Carousel) {
        imageList.add(carousel)
        notifyDataSetChanged()
    }
}

