package com.example.task8_music_player.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import com.example.task8_music_player.AsyncCallback
import com.example.task8_music_player.MusicAidl
import com.example.task8_music_player.R
import com.example.task8_music_player.model.Song
import com.example.task8_music_player.repository.Repository
import com.example.task8_music_player.util.NotificationHelper
import com.example.task8_music_player.util.PlayerMediaSessionCallback

class MusicService: Service() {

    private lateinit var playlist: List<Song>

    private lateinit var notificationHelper: NotificationHelper
    var currentPosition = 0
    private var previousPosition = -1
    var currentPlayer: MediaPlayer? = null

    private var isFirstStart = true

    private lateinit var sessionCallback: PlayerMediaSessionCallback

    override fun onCreate() {
        super.onCreate()
        if (!Repository.active) Repository.startRepository(baseContext)
        notificationHelper = NotificationHelper(baseContext)
        playlist = Repository.songs
        sessionCallback =  PlayerMediaSessionCallback(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isFirstStart) {
            val notification = notificationHelper.getNotificationBuilder(
                service = this,
                playPauseDrawableRes = PLAY_ICON
            ).build()
            startForeground(
                NotificationHelper.NOTIFICATION_ID, notification
            )
            isFirstStart = false
        }
        Toast.makeText(this, intent?.action.toString(), Toast.LENGTH_SHORT).show()
        when (intent?.action) {
            "play" -> this@MusicService.play(currentPosition)
            "previous" -> this@MusicService.previous()
            "next" -> this@MusicService.next()
            "stop" -> this@MusicService.stop()
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    private val binder = object : MusicAidl.Stub() {
        override fun isPlaying(): Boolean = currentPlayer?.isPlaying ?: true

        override fun play(position: Int, callback: AsyncCallback?) {
            currentPosition = position
            callback?.onSuccess(previousPosition)
            this@MusicService.play(position)
        }

        override fun stop() = this@MusicService.stop()

        override fun currentSongPosition() = previousPosition

    }

    fun play(position: Int?) {
        val isMusicPlaying = currentPlayer?.isPlaying?: false
        when {
            isMusicPlaying && position == previousPosition -> {
                pause()
            }
            isMusicPlaying && position != previousPosition -> {
                stop()
                playSong()
            }
            !isMusicPlaying && position != previousPosition -> {
                playSong()
            }
            else -> {
                currentPlayer?.apply {
                    currentPlayer?.currentPosition?.let { seekTo(it) }
                    start()
                }
                notificationHelper.updateNotification(sessionCallback, "play" , currentPlayer?.currentPosition?.toLong()?:0L, currentPlayer?.duration?.toLong() ?: 0L, getCurrentSong(), PAUSE_ICON)
            }
        }
    }

    private fun playSong() {
        currentPlayer = createPlayer(getCurrentSong()).apply {
            prepare()
            start()
        }
        previousPosition = currentPosition
        notificationHelper.updateNotification(sessionCallback, "play", currentPlayer?.currentPosition?.toLong()?:0L, currentPlayer?.duration?.toLong() ?: 0L, getCurrentSong(), PAUSE_ICON)
    }


    fun next() {
        previousPosition = currentPosition
        if(currentPosition == -1) return
        currentPosition = if (currentPosition < playlist.size - 1) {
            currentPosition + 1
        } else {
            0
        }

        play(currentPosition)
    }

    fun previous() {
        previousPosition = currentPosition
        if(currentPosition == -1) return
        currentPosition = if (currentPosition > 0) {
            currentPosition - 1
        } else {
            Repository.songs.size - 1
        }
        play(currentPosition)
    }

    fun pause() {
        currentPlayer?.pause()
        notificationHelper.updateNotification(sessionCallback, "pause", currentPlayer?.currentPosition?.toLong()?:0L, currentPlayer?.duration?.toLong() ?: 0L, getCurrentSong(), PLAY_ICON)
    }


    fun stop() {
        currentPlayer?.stop()
        currentPlayer?.release()
    }

    private fun getCurrentSong() = Repository.getSong(currentPosition)

//    fun isPlaying(): Boolean = currentPlayer?.isPlaying ?:true

    private fun createPlayer(song: Song?) =
        MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            song?.uri?.let { setDataSource(baseContext, it) }
        }

    companion object {
        private const val PAUSE_ICON = R.drawable.ic_baseline_pause_24
        private const val PLAY_ICON = R.drawable.ic_baseline_play_arrow_24
        val PENDING_INTENT_FLAG = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
    }
}