package com.example.workmanager

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(private val context: Context, workParams: WorkerParameters) : Worker(context, workParams) {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun doWork(): Result {

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(context,"first").apply {
            setContentTitle("Background Task")
            setContentText("Simple notification")
            setSmallIcon(R.drawable.ic_launcher_background)
            priority = NotificationCompat.PRIORITY_MAX
            priority = NotificationManager.IMPORTANCE_HIGH
            setDefaults(Notification.DEFAULT_ALL)

        }.build()

        nm.notify(System.currentTimeMillis().toInt(),notification)

        return Result.success()
    }

}