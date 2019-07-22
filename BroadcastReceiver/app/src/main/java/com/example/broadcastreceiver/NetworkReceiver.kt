package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NetworkReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                Toast.makeText(context,"Airplane Mode Triggered",Toast.LENGTH_LONG).show()
            }
        }
    }
}