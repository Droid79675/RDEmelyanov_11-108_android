package com.example.task7_camera

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

class CustomActivityContract: ActivityResultContract<Uri, Uri?>() {
    override fun createIntent(context: Context, input: Uri): Intent {
        val galleryIntent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            .putExtra(MediaStore.EXTRA_OUTPUT, input)

        val intent: Intent = Intent.createChooser(galleryIntent, "Выберите фото")

        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent.takeIf {
            resultCode == Activity.RESULT_OK  }?.data
    }
}