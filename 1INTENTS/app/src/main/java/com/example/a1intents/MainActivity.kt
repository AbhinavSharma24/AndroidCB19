package com.example.a1intents

import android.content.Intent
import android.net.Uri
//import android.support.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

const val KEY_1 = "Name"
class MainActivity : AppCompatActivity(), View.OnClickListener {

    //val btnViews = arrayOfNulls<Button>(4)
    //val hashMap : Pair<String,String> = Pair("a","b")

    lateinit var et1: EditText
    //val KEY_1 = "Name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnViews = arrayOf(btn1)

        // btnViews[0]=btn1
        // btnViews[1]=btn2
        // btnViews[2]=btn3
        // btnViews[3]=btn4

        btnViews.forEach {
            it?.setOnClickListener(this)
        }
        et1 = findViewById(R.id.name)

    }
    override fun onClick(v: View?){

//        for explicit intent (from activity A to activity B)
        val i = Intent(this,Main2Activity::class.java)
        i.putExtra(KEY_1,et1.text.toString())
        startActivity(i)

        //to open a webpage
        //val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://${et1.text.toString()}"))
        //startActivity(i)

        //to open dialpad
        //val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${et1.text.toString()}"))
        //startActivity(i)

        //TO OPEN GMAIL
        //val i = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${et1.text.toString()}"))
        //i.putExtra(Intent.EXTRA_SUBJECT,"Hello There!")
        //i.putExtra(Intent.EXTRA_TEXT,"Bye Bye")
        //startActivity(Intent.createChooser(i,"Send Email"))

//        TO CHECK ALL OPTIONS FOR CREATE CHOOSER
//        val i = Intent(Intent.ACTION_VIEW)
//        i.putExtra(Intent.EXTRA_TEXT,"Bye Bye")
//        startActivity(Intent.createChooser(i,"Send Email"))



    }
}
