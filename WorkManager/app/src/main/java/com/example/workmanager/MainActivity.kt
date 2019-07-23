package com.example.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            nm.createNotificationChannel(NotificationChannel("first","default",NotificationManager.IMPORTANCE_HIGH))
        }
        requestOneTimeTask()
        //requestRepetiveTask()
    }

    /*private fun requestRepetiveTask() {
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.MINUTES) {

        }
    }*/

    private fun requestOneTimeTask() {
        val constraints = Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.UNMETERED)
            setRequiresCharging(true)
            /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                setRequiresDeviceIdle(false)
            }*/
            setRequiresStorageNotLow(true)
        }

        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(1,TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}