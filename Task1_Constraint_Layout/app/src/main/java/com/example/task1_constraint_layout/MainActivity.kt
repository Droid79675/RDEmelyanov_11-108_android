package com.example.task1_constraint_layout

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.task1_constraint_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        button = findViewById(R.id.btn_gone)
        val iv: ImageView = findViewById(R.id.iv_main_avatar)

        button?.setOnClickListener {
            if(iv.visibility == ImageView.VISIBLE) iv.visibility = ImageView.INVISIBLE
            else iv.visibility = ImageView.VISIBLE
        }

    }
}