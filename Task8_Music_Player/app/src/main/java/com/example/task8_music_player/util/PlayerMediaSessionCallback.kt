package com.example.task8_music_player.util

import android.support.v4.media.session.MediaSessionCompat
import android.widget.Toast
import com.example.task8_music_player.service.MusicService

class PlayerMediaSessionCallback(private val musicService: MusicService) : MediaSessionCompat.Callback() {
    override fun onPause() {
        musicService.pause()
    }
    override fun onSeekTo(pos: Long) {
        musicService.currentPlayer?.seekTo(pos.toInt())
    }
}