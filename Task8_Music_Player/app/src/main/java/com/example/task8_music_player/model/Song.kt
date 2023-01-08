package com.example.task8_music_player.model

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song  (val id:Int, val name:String, @DrawableRes val cover: Int, val uri: Uri,
                  val singer:String, val genre:String): Parcelable
