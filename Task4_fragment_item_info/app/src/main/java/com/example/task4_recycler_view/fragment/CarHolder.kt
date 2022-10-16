package com.example.task4_recycler_view.fragment

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.task4_recycler_view.R
import com.example.task4_recycler_view.databinding.ItemCarBinding
import com.example.task4_recycler_view.fragment.CarInfoFragment
import com.example.task4_recycler_view.model.Car

class CarHolder (private val binding: ItemCarBinding, private val glide: RequestManager, private val onItemClick: (Car) -> Unit, )
: RecyclerView.ViewHolder(binding.root) {
    fun onBind(car: Car) {
        with(binding) {
            tvName.text = car.name
            val id = car.id
            root.setOnClickListener {
                onItemClick(car)
                btnCar.findNavController().navigate(R.id.action_carFragment_to_carInfoFragment,
                CarInfoFragment.createBundle(id.toString()))
            }
    
            glide
                .load(car.url)
                .placeholder(R.drawable.img)
                .error(R.drawable.img)
                .into(ivCarAvatar)
        }
    }




}