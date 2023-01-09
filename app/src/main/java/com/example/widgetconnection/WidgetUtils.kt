package com.example.widgetconnection

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import java.util.*


class WidgetUtils {
    private val ALARM_ID = 0
    private val INTERVAL_MILLIS : Long = 10000

    fun scheduleUpdate(context: Context) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.MILLISECOND, INTERVAL_MILLIS.toInt())

        val alarmIntent = Intent(context, RandomNumberWidget::class.java).let { intent ->
            //intent.action = AppWidget.ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        with(context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager) {
            setRepeating(AlarmManager.RTC,calendar.timeInMillis, INTERVAL_MILLIS ,alarmIntent)
        }
    }
}