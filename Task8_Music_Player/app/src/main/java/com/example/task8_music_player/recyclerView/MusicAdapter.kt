package com.example.task8_music_player.recyclerView

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.task8_music_player.model.MusicPlayerSong

class MusicAdapter(
    private val songActionCallback: SongActionCallback
    ) : ListAdapter<MusicPlayerSong, SongHolder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder.create(parent, songActionCallback)
    }

    override fun onBindViewHolder(holder: SongHolder, i: Int) {
        holder.onBind(currentList[i])
    }

    override fun onBindViewHolder(
        holder: SongHolder,
        i: Int,
        list: MutableList<Any>
    ) {
        if(list.isEmpty()) {
            super.onBindViewHolder(holder, i, list)
        } else {
            list.last().takeIf {
                it is Bundle }.let {
                holder.updateField(it as Bundle)
            }
        }
    }

    object Comparator : DiffUtil.ItemCallback<MusicPlayerSong>() {
        override fun areItemsTheSame(oldItem: MusicPlayerSong, newItem: MusicPlayerSong): Boolean {
            return oldItem.song.id == newItem.song.id
        }

        override fun areContentsTheSame(oldItem: MusicPlayerSong, newItem: MusicPlayerSong): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: MusicPlayerSong, newItem: MusicPlayerSong): Any? {
            return if (oldItem.active == newItem.active) {
                super.getChangePayload(oldItem, newItem)
            } else {
                Bundle().apply {
                    putBoolean(BUNDLE_KEY, newItem.active)
                }
            }
        }
    }

    companion object {
        const val BUNDLE_KEY = "BUNDLE_KEY"
    }
}