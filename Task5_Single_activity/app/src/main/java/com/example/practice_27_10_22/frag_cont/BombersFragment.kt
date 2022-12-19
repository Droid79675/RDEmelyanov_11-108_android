package com.example.practice_27_10_22.frag_cont

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.practice_27_10_22.R
import com.example.practice_27_10_22.databinding.FragmentBombersBinding
import com.example.practice_27_10_22.helper.NavigationFragmentInterface
import com.example.practice_27_10_22.helper.getClassName

class BombersFragment : Fragment(R.layout.fragment_bombers) {
    private var _binding: FragmentBombersBinding? = null
    private val binding get() = _binding!!
    private var className: String? = null
    private var title: TextView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        className = arguments?.getString(ARG)?: BombersFragment.getClassName()
        title = view.findViewById(R.id.tv_fragment_title)
        title?.text = className
        _binding = FragmentBombersBinding.bind(view)
        val returnButton: View = binding.includerBombers.returnButton
        returnButton.setOnClickListener {
            findNavController().navigate(R.id.action_bombersFragment_to_fragmentContainer) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object: NavigationFragmentInterface {
        override val ARG: String = "Bombers"
        override fun createBundle(name: String) = Bundle().apply {
            putString(ARG, name)
        }
    }
}