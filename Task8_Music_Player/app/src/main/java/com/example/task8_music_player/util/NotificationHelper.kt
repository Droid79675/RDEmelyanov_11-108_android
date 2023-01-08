package com.example.task8_music_player.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.example.task8_music_player.MainActivity
import com.example.task8_music_player.R
import com.example.task8_music_player.model.Song
import com.example.task8_music_player.service.MusicService

class NotificationHelper(private val context: Context) {

    private lateinit var notificationBuilder: NotificationCompat.Builder
    private var notificationManager: NotificationManager? = null
    private var mediaSession: MediaSessionCompat? = null
    private var playPendingIntent: PendingIntent? = null
    private var stopPendingIntent: PendingIntent? = null
    private var nextPendingIntent: PendingIntent? = null
    private var prevPendingIntent: PendingIntent? = null

    init {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mediaSession = MediaSessionCompat(context, MEDIA_SESSION_TAG)
        playPendingIntent = createPendingIntent("play")
        stopPendingIntent = createPendingIntent("stop")
        nextPendingIntent = createPendingIntent("next")
        prevPendingIntent = createPendingIntent("previous")
    }

    fun getNotificationBuilder(
        service: MusicService,
        @DrawableRes playPauseDrawableRes: Int
    ): NotificationCompat.Builder {
        createChannel()
        val notificationPendingIntent = PendingIntent.getActivity(
            context, NOTIFICATION_REQUEST_CODE, Intent(context, MainActivity::class.java), PENDING_INTENT_FLAG
        )
        val stateBuilder = PlaybackStateCompat.Builder()
            .setState(
                PlaybackStateCompat.STATE_PLAYING,
                15, 1f
            )

        mediaSession?.setPlaybackState(stateBuilder.build())

        notificationBuilder = NotificationCompat.Builder(
            context,
            NOTIFICATION_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession?.sessionToken)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(notificationPendingIntent)
            .setOnlyAlertOnce(true)
            .setOngoing(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(R.drawable.ic_baseline_skip_previous_24, "previous", prevPendingIntent)
            .addAction(playPauseDrawableRes, "play", playPendingIntent)
            .addAction(R.drawable.ic_baseline_skip_next_24, "next", nextPendingIntent)
            .addAction(R.drawable.ic_baseline_stop_24, "stop", stopPendingIntent)
        return notificationBuilder
    }

    fun updateNotification(sessionCallback: PlayerMediaSessionCallback, action: String, currentPos: Long, songDuration: Long, songModel: Song?, @DrawableRes playPauseDrawableRes: Int) {
        mediaSession?.setCallback(sessionCallback)
        mediaSession?.setMetadata(
            MediaMetadataCompat.Builder()
                .putString(
                    MediaMetadataCompat.METADATA_KEY_ARTIST,
                    songModel?.singer
                )
                .putString(
                    MediaMetadataCompat.METADATA_KEY_ALBUM,
                    songModel?.genre
                )
                .putString(
                    MediaMetadataCompat.METADATA_KEY_TITLE,
                    songModel?.name
                )
                .putLong(
                    MediaMetadataCompat.METADATA_KEY_DURATION,
                    songDuration
                )
                .build()
        )

        notificationBuilder.clearActions()
        notificationBuilder
            .addAction(R.drawable.ic_baseline_skip_previous_24, "previous", prevPendingIntent)
            .addAction(playPauseDrawableRes, "play", playPendingIntent)
            .addAction(R.drawable.ic_baseline_skip_next_24, "next", nextPendingIntent)
            .addAction(R.drawable.ic_baseline_stop_24, "stop", stopPendingIntent)
        updateMediaPlaybackState(currentPos)
        notificationManager?.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun updateMediaPlaybackState(currentPosition: Long) {
        mediaSession?.setPlaybackState(
            PlaybackStateCompat.Builder()
                .setActions(
                    PlaybackStateCompat.ACTION_PLAY
                            or PlaybackStateCompat.ACTION_PLAY_PAUSE
                            or PlaybackStateCompat.ACTION_PAUSE
                            or PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                            or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                            or PlaybackStateCompat.ACTION_SEEK_TO
                )
                .setState(
                    PlaybackStateCompat.STATE_PLAYING,
                    currentPosition,
                    1f,
                    SystemClock.elapsedRealtime()
                )
                .build()
        )
    }

    @PlaybackStateCompat.State
    private fun getPlaybackState(action: String): Int {
        return when (action) {
            "play" -> PlaybackStateCompat.STATE_PLAYING
            "pause" -> PlaybackStateCompat.STATE_PAUSED
            else -> PlaybackStateCompat.STATE_NONE
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Music channel"
            val description = "Channel for music"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)
            channel.description = description
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createPendingIntent(action: String): PendingIntent {
        return PendingIntent.getService(
        context, NOTIFICATION_REQUEST_CODE,
        Intent(context, MusicService::class.java).setAction(
            action
        ), MusicService.PENDING_INTENT_FLAG)
    }


    companion object {
        const val NOTIFICATION_REQUEST_CODE = 1
        const val NOTIFICATION_ID = 2
        const val MEDIA_SESSION_TAG = "MEDIA_SESSION_TAG"
        private const val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"
        val PENDING_INTENT_FLAG = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_CANCEL_CURRENT
        }
    }
}