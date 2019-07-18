package com.example.loginmovies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_signup.*

var username: String = ""
var password: String = ""
var fullname: String = ""
var email: String = ""


class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val prefs = getSharedPreferences("A", Context.MODE_PRIVATE)

        signupbtn1.setOnClickListener {
            username = edituser1.text.toString()
            password = editpass1.text.toString()
            fullname = editname1.text.toString()
            email = editemail1.text.toString()
            if(edituser1.text.toString() == "" || editpass1.text.toString() == "" || editname1.text.toString() == "" || editemail1.text.toString() == "") {
                Toast.makeText(this, "Name/Username/Password cannot be empty!!!", Toast.LENGTH_SHORT).show()
            }
            else {
                prefs.edit {
                    putString("FullName", fullname)
                    putString("Username", username)
                    putString("Password", password)
                }
                Toast.makeText(this, "Great, now SignIn to continue", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))

                edituser1.setText("")
                editpass1.setText("")
                editname1.setText("")
                editemail1.setText("")
            }
        }
    }
}
