package com.example.asynctaskcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random

class Main2Activity : AppCompatActivity() {

    val uiscope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            uiscope.launch {
                for (i in 0..10) {
                    delay(5000)
                    tv1.text = "$i"
                }
            }
        }
        button2.setOnClickListener {
            uiscope.launch{
                val result = get("https://jsonplaceholder.typicode.com/posts")
                tv2.text = result
            }
        }
    }
    private fun randNumber() = Random(System.currentTimeMillis()).nextInt()

    private suspend fun get(urlString:String):String{
        return withContext(Dispatchers.IO){
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            val isr = InputStreamReader(connection.inputStream)
            val bufferReader = BufferedReader(isr)
            val sb = StringBuilder()
            var buffer:String? = ""
            while (buffer != null) {
                sb.append(buffer)
                buffer = bufferReader.readLine()
            }
            sb.toString()
        }
    }

    override fun onDestroy() {
        uiscope.cancel()
        super.onDestroy()
    }
}
