package com.example.alarmactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationClass : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Alarm Toast",Toast.LENGTH_LONG).show()
    }

}