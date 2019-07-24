package com.example.unittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener {
            val km = et1.text.toString().toFloat()
            val min = et2.text.toString().toInt()

            val calcFare = fare(km,min)
            tv1.text = calcFare.toString()
        }
        if (intent.getStringExtra("RESULT") != null) {
            tv1.text = intent.getStringExtra("RESULT")
        }
    }

    private fun fare(km: Float, min : Int) {

    }
}
