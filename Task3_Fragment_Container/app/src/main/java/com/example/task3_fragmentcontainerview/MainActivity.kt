package com.example.task3_fragmentcontainerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task3_fragmentcontainerview.databinding.ActivityMainBinding
import com.example.task3_fragmentcontainerview.fragments.FirstFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val containerID: Int = R.id.main_fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if(savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction().setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out,
            android.R.anim.fade_in,
            android.R.anim.fade_out)
            .add(
            containerID,
            FirstFragment.getInstance(Bundle()),
            FirstFragment.TAG_FIRST_FRAGMENT)
            .addToBackStack(null)
            .commit()
    }
}