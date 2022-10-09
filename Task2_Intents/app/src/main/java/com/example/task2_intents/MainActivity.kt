package com.example.task2_intents

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.widget.Button
import com.example.task2_intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private var binding: ActivityMainBinding? = null
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater).also{
//            setContentView(it.root)
//        }


        button1 = findViewById(R.id.btn_add_alarm)
        button2 = findViewById(R.id.btn_add_calendar_event)
        button3 = findViewById(R.id.btn_open_webPage)

        button1?.setOnClickListener{
            createAlarm(true,10, 30, "AM")
        }

        button2?.setOnClickListener{
            addEvent("Экзамен","1311", 0,50000)
        }

        button3?.setOnClickListener{
            showMap(Uri.parse("geo:55.80350421708634,49.10587287924055"))
        }

    }

    private fun createAlarm(vibrate: Boolean, hour: Int, minutes: Int, am_pm: String) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_VIBRATE, vibrate)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
            putExtra(AlarmClock.EXTRA_IS_PM, am_pm)
        }
        val chooser: Intent = Intent.createChooser(intent, "Добавить будильник")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
            this.finish()
        }
    }

    private fun addEvent(title: String, location: String, begin: Int, end: Int) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        }
        val chooser: Intent = Intent.createChooser(intent, "Добавить событие в календарь")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
            this.finish()
        }
    }

    private fun showMap(geoLocation: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        val chooser: Intent = Intent.createChooser(intent, "Открыть карту")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
            this.finish()
        }
    }
}