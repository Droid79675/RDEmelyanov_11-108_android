package com.example.task4_recycler_view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.task4_recycler_view.databinding.ItemCarBinding
import com.example.task4_recycler_view.model.Car

class CarAdapter(private var list: List<Car>, private val glide: RequestManager, private val onItemClick: (Car) -> Unit,
): RecyclerView.Adapter<CarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder = CarHolder(
        binding = ItemCarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        onItemClick = onItemClick,
    )


    override fun onBindViewHolder(
        holder: CarHolder,
        position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
    fun updateData(newList: List<Car>){
        list = newList
        notifyDataSetChanged()
    }
}