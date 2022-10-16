package com.example.task4_recycler_view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_car.toolbar
import com.example.task4_recycler_view.R
import com.example.task4_recycler_view.data.CarRepository
import com.example.task4_recycler_view.databinding.FragmentCarBinding

class CarFragment: Fragment(R.layout.fragment_car) {

    private var _binding: FragmentCarBinding? = null
    private val binding get() = _binding!!

    private var adapter: CarAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCarBinding.bind(view)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = CarAdapter(
            CarRepository.cars,
            Glide.with(this),

        ){}
        binding.rvCars.adapter = adapter
        binding.rvCars.layoutManager = GridLayoutManager(requireContext(), 1)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}