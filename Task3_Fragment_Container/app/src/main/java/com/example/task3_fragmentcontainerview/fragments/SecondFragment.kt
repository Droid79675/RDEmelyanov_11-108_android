package com.example.task3_fragmentcontainerview.fragments

import androidx.fragment.app.Fragment
import com.example.task3_fragmentcontainerview.databinding.FragmentSecondBinding
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.task3_fragmentcontainerview.Constants
import com.example.task3_fragmentcontainerview.R


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //достаём из bundle
        val counterValue = arguments?.get(Constants.COUNTER_KEY) ?: 0

        with(binding) {

            when (counterValue) {
                in 0..50 -> {
                    mainScreen.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.lime_green))
                }
                in 51..100 -> {
                    mainScreen.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.blue))
                }
                in 100..Integer.MAX_VALUE ->{
                    mainScreen.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.magenta))
                }
            }

            tvCounterValue.text = "Counter value: $counterValue"

        }
    }

    companion object {
        const val TAG_SECOND_FRAGMENT = "TAG_SECOND_FRAGMENT"
        fun getInstance(bundle: Bundle?): SecondFragment {
            val secondFragment = SecondFragment()
            secondFragment.arguments = bundle
            return secondFragment
        }
    }
}