package com.example.task2_intents

import android.os.Bundle
import android.provider.AlarmClock.*
import android.provider.CalendarContract.EXTRA_EVENT_BEGIN_TIME
import android.provider.CalendarContract.EXTRA_EVENT_END_TIME
import android.provider.CalendarContract.Events.EVENT_LOCATION
import android.provider.CalendarContract.Events.TITLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {

    private var textView1: TextView? = null
    private var textView2: TextView? = null
    private var textView3: TextView? = null
    private var textView4: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var vibration: Boolean = intent.getBooleanExtra(EXTRA_VIBRATE, false)
        var hour: Int = intent.getIntExtra(EXTRA_HOUR, 0)
        var minutes: Int = intent.getIntExtra(EXTRA_MINUTES, 0)
        var am_or_pm: String? = intent.getStringExtra(EXTRA_IS_PM)


        var title: String? = intent.getStringExtra(TITLE)
        var location: String? = intent.getStringExtra(EVENT_LOCATION)
        var begin_time: Int = intent.getIntExtra(EXTRA_EVENT_BEGIN_TIME, 0)
        var end_time: Int = intent.getIntExtra(EXTRA_EVENT_END_TIME, 0)

        var geo_location: String? = intent.data.toString()


        textView1 = findViewById(R.id.tv_1)
        textView2 = findViewById(R.id.tv_2)
        textView3 = findViewById(R.id.tv_3)
        textView4 = findViewById(R.id.tv_4)
        if(vibration != null) textView1?.text = vibration.toString()

        if(title != null) textView1?.text = title

        if(geo_location != null) textView1?.text = geo_location

        if(hour != null) textView2?.text = hour.toString()

        if(location != null) textView2?.text = location

        if(minutes != null) textView3?.text = minutes.toString()

        if(begin_time >= 0 && minutes == null) textView3?.text = begin_time.toString()

        if(am_or_pm != null) textView4?.text = am_or_pm

        if(end_time != 0) textView4?.text = end_time.toString()



    }
}