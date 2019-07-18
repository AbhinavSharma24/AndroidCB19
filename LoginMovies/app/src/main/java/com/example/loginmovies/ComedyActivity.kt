package com.example.loginmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.view.size
import kotlinx.android.synthetic.main.activity_genre.*

class ComedyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comedy)

        val adapter = ArrayAdapter(this,R.layout.mytextview,
                arrayOf("Monty Python and the Holy Grail","Office Space","Ferris Bueller's Day Off","Young Frankenstein",
                    "Shaun of the Dead","Superbad","The 40 Year-Old Virgin","Anchorman: The Legend of Ron Burgundy",
                    "Airplane!","Blazing Saddles","The Big Lebowski","Ghostbusters","Forrest Gump","The Princess Bride")
            )
        listView.adapter = adapter
    }
}
