package com.example.loginmovies

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_genre.*

val genre = arrayOf("Comedy","Horror","Drama","Action","Animated","Science Fiction","Romantic","Adventure","Thriller","Documentary","Mystery")

class GenreActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)

        val prefs = getSharedPreferences("A", Context.MODE_PRIVATE)

        nametv.text = "Welcome " + prefs.getString("FullName","") + " !!!"

        val adapter = ArrayAdapter(this,R.layout.mytextview, genre)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            if(position == 0)
                startActivity(Intent(this,ComedyActivity::class.java))
//            else if(position == 1)
//                startActivity(Intent(this,HorrorActivity::class.java))
        }
    }
}
