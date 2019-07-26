package com.example.a1calculatorkotlin

import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var a: Float = 0.toFloat()
    var b: Float = 0.toFloat()
    var text1: TextView? = null
    var text2: TextView? = null

    var Addition: Boolean = false
    var Subtraction: Boolean = false
    var Multiply: Boolean = false
    var Divide: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text1 = tv1
        text2 = tv2

        onebtn.setOnClickListener(this)
        twobtn.setOnClickListener(this)
        threebtn.setOnClickListener(this)
        fourbtn.setOnClickListener(this)
        fivebtn.setOnClickListener(this)
        sixbtn.setOnClickListener(this)
        sevenbtn.setOnClickListener(this)
        eightbtn.setOnClickListener(this)
        ninebtn.setOnClickListener(this)
        zerobtn.setOnClickListener(this)

        sum.setOnClickListener(this)
        minus.setOnClickListener(this)
        multiply.setOnClickListener(this)
        divide.setOnClickListener(this)
        dot.setOnClickListener(this)
        equal.setOnClickListener(this)
        clear.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.onebtn)
            text2!!.setText(text2!!.text.toString() + "1")
        if (v.id == R.id.twobtn)
            text2!!.setText(text2!!.text.toString() + "2")
        if (v.id == R.id.threebtn)
            text2!!.setText(text2!!.text.toString() + "3")
        if (v.id == R.id.fourbtn)
            text2!!.setText(text2!!.text.toString() + "4")
        if (v.id == R.id.fivebtn)
            text2!!.setText(text2!!.text.toString() + "5")
        if (v.id == R.id.sixbtn)
            text2!!.setText(text2!!.text.toString() + "6")
        if (v.id == R.id.sevenbtn)
            text2!!.setText(text2!!.text.toString() + "7")
        if (v.id == R.id.eightbtn)
            text2!!.setText(text2!!.text.toString() + "8")
        if (v.id == R.id.ninebtn)
            text2!!.setText(text2!!.text.toString() + "9")
        if (v.id == R.id.zerobtn)
            text2!!.setText(text2!!.text.toString() + "0")

        if (v.id == R.id.sum) {
            if (text2 == null) {
                text2!!.setText("")
            } else {
                a = java.lang.Float.parseFloat(text2!!.text.toString() + "")
                Addition = true
                text1!!.setText(text2!!.text.toString() + " + ")
                text2!!.text = null
            }
        }
        if (v.id == R.id.minus) {
            if (text2 == null) {
                text2!!.setText("")
            } else {
                a = java.lang.Float.parseFloat(text2!!.text.toString() + "")
                Subtraction = true
                text1!!.setText(text2!!.text.toString() + " - ")
                text2!!.text = null
            }
        }
        if (v.id == R.id.multiply) {
            if (text2 == null) {
                text2!!.setText("")
            } else {
                a = java.lang.Float.parseFloat(text2!!.text.toString() + "")
                Multiply = true
                text1!!.setText(text2!!.text.toString() + " * ")
                text2!!.text = null
            }
        }
        if (v.id == R.id.divide) {
            if (text2 == null) {
                text2!!.setText("")
            } else {
                a = java.lang.Float.parseFloat(text2!!.text.toString() + "")
                Divide = true
                text1!!.setText(text2!!.text.toString() + " ÷ ")
                text2!!.text = null
            }
        }

        if (v.id == R.id.equal) {
            b = java.lang.Float.parseFloat(text2!!.text.toString() + "")
            if (text2!!.text != "" && text1!!.text != "") {

                if (Addition == true) {
                    text2!!.setText((a + b).toString() + "")
                    text1!!.setText("")
                    Addition = false
                }

                if (Subtraction == true) {
                    text2!!.setText((a - b).toString() + "")
                    text1!!.setText("")
                    Subtraction = false
                }

                if (Multiply == true) {
                    text2!!.setText((a * b).toString() + "")
                    text1!!.setText("")
                    Multiply = false
                }

                if (Divide == true) {
                    text2!!.setText((a / b).toString() + "")
                    text1!!.setText("")
                    Divide = false
                }
            }
            else{
                Toast.makeText(this,"Enter a number please", Toast.LENGTH_SHORT).show()
            }
        }

        if (v.id == R.id.clear) {
            text2!!.setText("")
            text1!!.setText("")
        }
        if (v.id == R.id.dot) {
            text2!!.setText(text2!!.text.toString() + ".")
        }
    }
}
