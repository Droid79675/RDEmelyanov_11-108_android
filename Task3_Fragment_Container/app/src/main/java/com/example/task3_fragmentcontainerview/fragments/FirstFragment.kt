package com.example.task3_fragmentcontainerview.fragments

import androidx.fragment.app.Fragment
import com.example.task3_fragmentcontainerview.databinding.FragmentFirstBinding
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.task3_fragmentcontainerview.Constants


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backupValueCounter()
        binding.counter = counter
        with(binding) {

            btnOpenDialogFragment.setOnClickListener {
                val dialog = DialogFragment(counterValue = counter

                ) { counterFromDialogFragment ->
                    counter = counterFromDialogFragment
                    binding.counter = counter

                    arguments?.apply {
                        putInt(Constants.COUNTER_KEY, counterFromDialogFragment)
                    }
                }
                dialog.show(parentFragmentManager, "Dialog fragment")
            }

            btnAddVariableToCounter.setOnClickListener {
                counter++
                binding.counter = counter

                arguments?.apply {
                    putInt(Constants.COUNTER_KEY, counter)
                }
            }
            btnGoToSecondFragment.setOnClickListener {
                parentFragmentManager.beginTransaction().replace(
                    Constants.containerId,
                    SecondFragment.getInstance(arguments),
                    SecondFragment.TAG_SECOND_FRAGMENT
                ).setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out,
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun backupValueCounter() {
        if (arguments != null)
            counter = arguments?.getInt(Constants.COUNTER_KEY) ?: 0
    }

    companion object {
        const val TAG_FIRST_FRAGMENT = "TAG_FIRST_FRAGMENT"
        fun getInstance(bundle: Bundle?): FirstFragment {
            val firstFragment = FirstFragment()
            firstFragment.arguments = bundle
            return firstFragment
        }
    }

}