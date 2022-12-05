package com.example.practice_27_10_22.frag_cont

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.practice_27_10_22.R
import com.example.practice_27_10_22.databinding.FragmentInterceptorsBinding
import com.example.practice_27_10_22.helper.NavigationFragmentInterface
import com.example.practice_27_10_22.helper.getClassName

class InterceptorsFragment : Fragment(R.layout.fragment_interceptors) {
    private var _binding: FragmentInterceptorsBinding? = null
    private val binding get() = _binding!!
    private var className: String? = null
    private var title: TextView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        className = arguments?.getString(ARG)?: InterceptorsFragment.getClassName()
        title = view.findViewById(R.id.tv_fragment_title)
        title?.text = className
        _binding = FragmentInterceptorsBinding.bind(view)
        val returnButton: View = binding.includerInterceptors.returnButton
        returnButton.setOnClickListener {
            findNavController().navigate(R.id.action_interceptorsFragment_to_fragmentContainer) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object: NavigationFragmentInterface {
        override val ARG: String = "Interceptors"
        override fun createBundle(name: String) = Bundle().apply {
            putString(ARG, name)
        }
    }
}