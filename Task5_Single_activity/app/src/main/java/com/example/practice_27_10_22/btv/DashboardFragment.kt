package com.example.practice_27_10_22.btv

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.practice_27_10_22.R
import com.example.practice_27_10_22.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)
    }
}