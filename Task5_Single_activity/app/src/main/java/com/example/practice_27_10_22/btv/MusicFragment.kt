package com.example.practice_27_10_22.btv

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import com.example.practice_27_10_22.R
import com.example.practice_27_10_22.databinding.FragmentMusicBinding

class MusicFragment : Fragment(R.layout.fragment_music) {
    private var _binding:FragmentMusicBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMusicBinding.bind(view)
    }
}