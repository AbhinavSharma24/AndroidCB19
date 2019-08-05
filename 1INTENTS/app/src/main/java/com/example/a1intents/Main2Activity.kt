package com.example.a1intents

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
//import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*


class Main2Activity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
         if(btn9.id == R.id.btn9){
            val connectService = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
             if (connectService.activeNetworkInfo.isConnected && connectService.activeNetworkInfo!=null)
                tv1.text = "connected"
             if(!connectService.activeNetworkInfo.isConnected)
                tv1.text = "Not Connected"
         }

    }

    lateinit var tv1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        val btnViews = arrayOf(btn9)
        val name = intent.getStringExtra(KEY_1)
        //tv1.text = name
        btnViews.forEach {
            it?.setOnClickListener(this)
        }
        tv1 = findViewById(R.id.tv1)



        //      fab.setOnClickListener { view ->
        //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //             .setAction("Action", null).show()
    }
}


   // override fun onClick(v: View?){

    //    startActivity(Intent(this,MainActivity::class.java))


  //  }



