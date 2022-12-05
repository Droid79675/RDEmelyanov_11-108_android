package com.example.practice_27_10_22.frag_cont

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.practice_27_10_22.R
import com.example.practice_27_10_22.databinding.FragmentContainerBinding

class FragmentContainer : Fragment(R.layout.fragment_container) {

    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContainerBinding.bind(view)
        with(binding){
            btnFighter.setOnClickListener{
                findNavController().navigate(R.id.action_fragmentContainer_to_fighterFragment)
            }
            btnInterceptor.setOnClickListener{
                findNavController().navigate(R.id.action_fragmentContainer_to_interceptorsFragment)
            }
            btnAttackPlane.setOnClickListener{
                findNavController().navigate(R.id.action_fragmentContainer_to_attackPlanesFragment)
            }
            btnBomber.setOnClickListener{
                findNavController().navigate(R.id.action_fragmentContainer_to_bombersFragment)
            }
        }

    }
}