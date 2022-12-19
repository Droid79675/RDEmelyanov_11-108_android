package com.example.task6_notifications

import android.app.Notification.CATEGORY_MESSAGE
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Log.d("OnReceive", "Got intent")
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0 , Intent(context, AlarmActivity::class.java), 0
        )

        val builder = NotificationCompat.Builder(context!!, "ALARM")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle("Прувет, я будильник")
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setContentText("Я будильник, я бужу")
            .setAutoCancel(true)
            .setCategory(CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, builder.build())
        Log.d("Alarm Ring", "Alarm just fired")
    }
}