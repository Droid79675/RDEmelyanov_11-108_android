package com.example.task8_music_player

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.task8_music_player.databinding.ActivityMainBinding
import com.example.task8_music_player.recyclerView.MusicAdapter
import com.example.task8_music_player.recyclerView.SongActionCallback
import com.example.task8_music_player.repository.Repository
import com.example.task8_music_player.service.MusicService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: MusicAdapter? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = MusicAidl.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Repository.startRepository(binding.root.context)
        setContentView(binding.root)

        adapter = MusicAdapter(object : SongActionCallback {
            override fun play(position: Int) {
                binder?.play(position, object : AsyncCallback.Stub() {
                    override fun onSuccess(index: Int) {
                        if(position != index) {
                            Repository.setPlay(position)
                            Repository.setPause(index)
                        } else {
                            Repository.setPause(index)
                            if(binder?.isPlaying == true) {
                                Repository.setPlay(index)
                            }
                        }
                        adapter?.submitList(Repository.getSongList())

                    }

                    override fun onError(error: AidlException?) {
                        TODO("Not yet implemented")
                    }

                })
            }

            override fun onSongClicked(position: Int) {
                TODO("Not yet implemented")
            }
        })

        adapter?.submitList(Repository.getSongList())
        binding.rvMusic.adapter = adapter
    }


    override fun onStart() {
        super.onStart()
        bindService(
            getMusicServiceIntent(),
            connection,
            android.app.Service.BIND_AUTO_CREATE
        )
        startService(getMusicServiceIntent())
    }

    private fun getMusicServiceIntent() = Intent(this@MainActivity, MusicService::class.java)

    companion object {
        private var binder: MusicAidl? = null
    }
}

