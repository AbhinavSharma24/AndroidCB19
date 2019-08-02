package com.example.asynctaskcoroutines

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
private val TAG = "PUI"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // For synchronous tasks
//        button1.setOnClickListener{
//            waitNSec(5)
//            tv1.text = "After 5 sec delay"
//        }

        // For Asynchronous tasks
        button1.setOnClickListener {
//            Handler().postDelayed(
//                {
//                    tv1.text = "After 10 sec delay"
//                }, 10000
//            )
            val timerTask = TimerTask()
            timerTask.execute(10)

       }

        button2.setOnClickListener {
            tv2.text = randNumber().toString()
        }
    }



       private fun randNumber() = Random(System.currentTimeMillis()).nextInt()

     private fun waitNSec(n: Int){
        val start = System.currentTimeMillis()
        while(System.currentTimeMillis()<(start+n*1000));
    }


    inner class TimerTask: AsyncTask<Int,Int,Unit>(){
        override fun doInBackground(vararg p0: Int?) {
          val n = p0[0]
            n?.let {
                for (i in 0..n){
                    waitNSec(1)
                    Log.d("TAG","After 10 sec")
                   // tv1.text = "After 10 seconds"
                    publishProgress(i)

                }
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val n = values[0]?:0
            tv1.text = "$n"
        }
        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
        }


    }
}


//fun main(){
////    val start = System.currentTimeMillis()
////    val c = AtomicLong()
////    for(i in 1..1_000_000L)
////        thread(start = true){
////            c.addAndGet(i)
////    }
//
//    val c = AtomicLong()
//    for (i in 1..1_000_000L)
//}