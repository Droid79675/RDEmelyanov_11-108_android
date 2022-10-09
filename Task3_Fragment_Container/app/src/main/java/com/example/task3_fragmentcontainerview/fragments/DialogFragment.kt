package com.example.task3_fragmentcontainerview.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.task3_fragmentcontainerview.databinding.FragmentDialogBinding


class DialogFragment(val counterValue: Int, val onButtonClicked: (Int) -> Unit) : DialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var editTextValue: Int = 0
        with(binding) {

            positiveButton.setOnClickListener {
                if (!binding.etCounter.text.isNullOrBlank()) editTextValue = Integer.valueOf(binding.etCounter.text.toString())

                if (checkValue()) onButtonClicked(counterValue + editTextValue)
            }
            neutralButton.setOnClickListener {
                dismiss()
            }
            negativeButton.setOnClickListener {
                if (!binding.etCounter.text.isNullOrBlank()) editTextValue = Integer.valueOf(binding.etCounter.text.toString())

                if (checkValue()) onButtonClicked(counterValue - editTextValue)

            }
        }
    }

    override fun onStart() {
        super.onStart()
        val metrics: DisplayMetrics = resources.displayMetrics
        val width: Int = metrics.widthPixels
        dialog?.window?.setLayout(width,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun checkValue() : Boolean{
        if (!binding.etCounter.text.isNullOrBlank()) {
            when (Integer.valueOf(binding.etCounter.text.toString())) {
                !in 0..100 -> {
                    binding.textInputLayout.error = "Невеный формат данных"
                    return false
                }
                else -> {
                    binding.textInputLayout.isErrorEnabled = false
                }
            }
        }
        return true
    }


}