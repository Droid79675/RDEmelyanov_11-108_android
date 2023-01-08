package com.example.task8_music_player.repository

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.annotation.RawRes
import com.example.task8_music_player.R
import com.example.task8_music_player.model.MusicPlayerSong
import com.example.task8_music_player.model.Song
import java.io.File

object Repository {
    var active = false
    private set
    private lateinit var musicPlayerSongs: MutableList<MusicPlayerSong>
    lateinit var songs: List<Song>
    fun getSongList(): MutableList<MusicPlayerSong> {
        return musicPlayerSongs.toMutableList()
    }

    fun getSong(i:Int): Song {
        return songs[i]
    }

    fun setPlay(i:Int) {
        val temp = musicPlayerSongs[i].copy()
        temp.active = true
        musicPlayerSongs[i] = temp
    }

    fun setPause(i:Int) {
        val temp = musicPlayerSongs[i].copy()
        temp.active = false
        musicPlayerSongs[i] = temp
    }

    fun startRepository(context: Context) {
        songs = listOf(Song(1, "Remorse", R.drawable.ic_baseline_hm2, parseToMusicUri(context, R.raw.remorse), "Scattle", "Top-down shooter"),
            Song(2, "Order", R.drawable.ic_baseline_order, parseToMusicUri(context, R.raw.heaven_pierce_her_order), "Heaven Pierce Her", "Experimental rock"),
            Song(3, "Turbo Killer", R.drawable.ic_baseline_turbo_killer, parseToMusicUri(context, R.raw.turbo_killer), "Carpenter Brut", "Electronic"))
        musicPlayerSongs = songs.map {
            MusicPlayerSong(it, false)
        }.toMutableList()
        active = true
    }

    fun Repository.parseToMusicUri(context: Context, @RawRes songId: Int): Uri = Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE +
                File.pathSeparator +
                File.separator +
                File.separator +
                context.packageName +
                File.separator +
                songId
    )
}