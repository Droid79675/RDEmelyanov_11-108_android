package com.example.practice_27_10_22.frag_cont

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.practice_27_10_22.R
import com.example.practice_27_10_22.databinding.FragmentFighterBinding
import com.example.practice_27_10_22.helper.NavigationFragmentInterface
import com.example.practice_27_10_22.helper.getClassName

class FighterFragment : Fragment(R.layout.fragment_fighter) {
    private var _binding: FragmentFighterBinding? = null
    private val binding get() = _binding!!
    private var className: String? = null
    private var title: TextView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        className = arguments?.getString(ARG)?: FighterFragment.getClassName()
        title = view.findViewById(R.id.tv_fragment_title)
        title?.text = className
        _binding = FragmentFighterBinding.bind(view)
        val returnButton: View = binding.includerFighter.returnButton
        returnButton.setOnClickListener {
            findNavController().navigate(R.id.action_fighterFragment_to_fragmentContainer) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object: NavigationFragmentInterface {
        override val ARG: String = "Fighters"
        override fun createBundle(name: String) = Bundle().apply {
            putString(ARG, name)
        }
    }
}