package com.example.loginmovies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class
MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("A", Context.MODE_PRIVATE)

        signinbtn.setOnClickListener {
            if(edituser.text.toString() == prefs.getString("Username", "") && editpass.text.toString() == prefs.getString("Password", ""))
                startActivity(Intent(this, GenreActivity::class.java))
            else
                Toast.makeText(this,"Incorrect Username/Password",Toast.LENGTH_SHORT).show()
        }
        exitbtn.setOnClickListener {
            finishAffinity()
        }
        signupbtn.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }
}
