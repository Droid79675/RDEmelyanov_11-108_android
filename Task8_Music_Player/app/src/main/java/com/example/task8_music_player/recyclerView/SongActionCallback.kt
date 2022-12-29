package com.example.task8_music_player.recyclerView

interface SongActionCallback {
    fun play(position: Int)
    fun onSongClicked(position: Int)
}