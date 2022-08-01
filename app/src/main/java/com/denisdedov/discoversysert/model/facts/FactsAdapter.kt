package com.example.ngenge.recyclerviewclicklistener

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denisdedov.discoversysert.R
import com.denisdedov.discoversysert.databinding.FactItemBinding
import com.denisdedov.discoversysert.model.routes.Fact


class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val binding = FactItemBinding.bind(itemView)

    val name = binding.tvTitle
    val image = binding.ivFactTitle


    fun bind(fact: Fact,clickListener: OnItemClickListener)
    {
        name.text = fact.title.toString()
        image.setImageResource(fact.imageId)

        itemView.setOnClickListener {
            clickListener.onItemClicked(fact)
        }
    }

}


class RecyclerAdapter(var facts:MutableList<Fact>, val itemClickListener: OnItemClickListener):RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fact_item,parent,false)
        return MyHolder(view)


    }

    override fun getItemCount(): Int {
        return facts.size
    }

    override fun onBindViewHolder(myHolder: MyHolder, position: Int) {
        val user = facts.get(position)
        myHolder.bind(user,itemClickListener)


    }
}


interface OnItemClickListener{
    fun onItemClicked(fact: Fact)
}