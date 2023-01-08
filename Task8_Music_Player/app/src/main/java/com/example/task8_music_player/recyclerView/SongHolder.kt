package com.example.task8_music_player.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task8_music_player.R
import com.example.task8_music_player.databinding.ItemMusicBinding
import com.example.task8_music_player.model.MusicPlayerSong

class SongHolder(
    private val binding: ItemMusicBinding,
    private val songActionCallback: SongActionCallback
    ) : RecyclerView.ViewHolder(binding.root) {

    init {
        with(binding) {
            btnPlay.setOnClickListener {
                songActionCallback.play(adapterPosition)
            }
            root.setOnClickListener {
                songActionCallback.onSongClicked(adapterPosition)
            }
        }
    }

    fun onBind(item: MusicPlayerSong) {
        with(binding) {
            playerSong = item
            imageView.setImageResource(item.song.cover)
        }
    }

    fun updateField(bundle: Bundle?) {
        if (bundle?.containsKey(MusicAdapter.BUNDLE_KEY) == true) {
            bundle.getBoolean(MusicAdapter.BUNDLE_KEY).also {
                when (it) {
                    false -> binding.btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    true -> binding.btnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
                }
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, songActionCallback: SongActionCallback) =
            SongHolder(
                ItemMusicBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                songActionCallback
            )
    }
}