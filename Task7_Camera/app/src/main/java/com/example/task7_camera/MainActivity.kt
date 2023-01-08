package com.example.task7_camera

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.task7_camera.databinding.ActivityMainBinding
import java.io.File

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var imageAsUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnAttachPhoto.setOnClickListener {
                imageAsUri = initTempUri()
                activityResultLauncher.launch(imageAsUri)
            }
        }
    }

    private val activityResultLauncher = registerForActivityResult(CustomActivityContract()){
        if(it == null) {
            binding.ivPhoto.setImageURI(imageAsUri)
        } else {
            binding.ivPhoto.setImageURI(it)
        }
    }

    private fun initTempUri(): Uri {
        val tempImagesDirectory = File(
            applicationContext.filesDir,
            "temp_images"
        )
        tempImagesDirectory.mkdir()
        val tempImage = File(
            tempImagesDirectory,
            "temp_image.jpg"
        )
        return FileProvider.getUriForFile(
            applicationContext,
            "com.example.task7_camera",
            tempImage
        )
    }

}