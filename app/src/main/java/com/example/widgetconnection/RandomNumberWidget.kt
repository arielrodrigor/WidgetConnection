package com.example.widgetconnection


import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import java.util.Random

class RandomNumberWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                appWidgetId: Int) {

        val views = RemoteViews(context.packageName, R.layout.random_number_widget)
        views.setTextViewText(R.id.appwidget_text, getRandomNumber())

        // Create an Intent to launch ExampleActivity when clicked
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)

        // Tell the AppWidgetManager to perform an update on the current app widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun getRandomNumber(): String {
        // Generate a random number
        val random = Random()
        return random.nextInt(100).toString()
    }
}
