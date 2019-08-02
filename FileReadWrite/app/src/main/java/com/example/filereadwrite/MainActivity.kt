package com.example.filereadwrite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("Files","Files dir ${getExternalFilesDir(Environment.getDownloadCacheDirectory().absolutePath)}")

        //Android App Data Directory
        //val file = File(getExternalFilesDir(Environment.getDownloadCacheDirectory().absolutePath),"myFile.text")

        //Downloads directory : Needs permission for this
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"myFile.text")

        createbtn.setOnClickListener {
            //file.writeText(editText.text.toString())
            file.appendText(editText.text.toString())
            editText.setText("")
            Log.i("Files","exists = ${file.exists()}")
        }

        restorebtn.setOnClickListener {
            val existingFile = file.readText()
            editText.setText(existingFile)
        }
    }
}
