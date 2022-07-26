package com.denisdedov.discoversysert.model.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FactItemBinding

class FactsAdapter: RecyclerView.Adapter<FactsAdapter.FactHolder>() {
    var factList = ArrayList<Fact>()
    class FactHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = FactItemBinding.bind(item)
        fun bind(fact: Fact) {
            binding.ivFactTitle.setImageResource(fact.imageId)
            binding.tvTitle.text = fact.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fact_item, parent, false)
        return FactHolder(view)
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        holder.bind(factList[position])
    }

    override fun getItemCount(): Int {
        return factList.size
    }

    fun addFact(fact: Fact) {
        factList.add(fact)
        notifyDataSetChanged()
    }

}