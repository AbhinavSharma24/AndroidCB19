package com.example.mysharedpreferences1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getPreferences(Context.MODE_PRIVATE)

        var appOpenCount = 0
        appOpenCount = prefs.getInt("Count", 0)
        appOpenCount++
        prefs.edit{
            putInt("Count",appOpenCount)
        }

        textview.text = appOpenCount.toString()



        savebutton.setOnClickListener {
                //prefs.edit().putString("Name", editText.text.toString()).apply()
            prefs.edit {
                putString("Name", editText.text.toString())
            }
        }
        restorebutton.setOnClickListener {
            editText.setText(prefs.getString("Name", ""))
        }
    }
}
