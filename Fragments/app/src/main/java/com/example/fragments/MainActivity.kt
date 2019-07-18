package com.example.fragments

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = Bundle()

        val frag = DCFragment()

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, DCFragment())
//            .commit()

        marvelbtn.setOnClickListener{
        val bundle = Bundle()
        val frag = DCFragment()
            bundle.putString("NAME", "MARVEL MOVIES")
            frag.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commit()
        }

        dcbtn.setOnClickListener {
            val bundle = Bundle()
            val frag = DCFragment()
            bundle.putString("NAME", "DC MOVIES")
            frag.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, frag)
                .commit()
        }
        }
    }

