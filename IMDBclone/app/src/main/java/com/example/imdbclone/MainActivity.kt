package com.example.imdbclone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofitClient.create(GithubService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        dummyEditTextFocus.requestFocus()
//        dummyEditTextFocus.isFocusableInTouchMode = true

        //Retrofit
        service.nowShowing().enqueue(object : Callback<Tmdb2> {
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed, check your internet connection !!!"
            }

            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = TmdbAdapter(this@MainActivity, response.body()!!.results)
                }
            }
        })
        service.popularMovies().enqueue(object : Callback<Tmdb2> {
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed!"
            }

            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    rview2.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter = TmdbAdapter2(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.upcoming().enqueue(object : Callback<Tmdb2> {
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed!"
            }

            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview3.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter = TmdbAdapter(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.toprated().enqueue(object : Callback<Tmdb2> {
            override fun onFailure(call: Call<Tmdb2>, t: Throwable) {
                tv.text="Loading failed!"
                //et.visibility = "gone".toInt()
            }

            override fun onResponse(
                call: Call<Tmdb2>,
                response: Response<Tmdb2>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview4.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview4.adapter = TmdbAdapter2(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        /*searchbutton.setOnClickListener {
            val a = Intent(this,Search::class.java)
            a.putExtra("Search Text",et.text.toString())
            startActivity(a)

        }*/

    }
}
