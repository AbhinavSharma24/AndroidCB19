package com.example.imdbclone

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_castdetails.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("Registered")
class CastDetails : AppCompatActivity() {
    //lateinit var mAnimator: ValueAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_castdetails)
        setSupportActionBar(toolbar11)
        supportActionBar?.title = "Cast Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pos = intent.getIntExtra("castID",0)
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofitClient.create(GithubService::class.java)

        service.castinfo(pos).enqueue(object : Callback<Castinfo> {
            override fun onFailure(call: Call<Castinfo>, t: Throwable) {
                tv.text="Loading failed!"
            }
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Castinfo>, response: Response<Castinfo>) {
                runOnUiThread {
                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + response.body()?.profile_path).into(img1)
                    progressBar.visibility = View.GONE
                }
            }
        })

        service.castinfo(pos).enqueue(object : Callback<Castinfo> {
            override fun onFailure(call: Call<Castinfo>, t: Throwable) {
                tv.text="Loading failed!"
            }
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<Castinfo>,
                response: Response<Castinfo>
            ) {
                runOnUiThread {
                    //toolbar.title=response.body()?.name
                    if(response.body()?.birthday != null && response.body()?.place_of_birth != null && response.body()?.biography != null) {
                        val age = 2019 - response.body()?.birthday?.substring(0, 4)!!.toInt()
                        tv.text = "\n\nAge : " + age + "\n\nBirthPlace : " + response.body()?.place_of_birth +
                                "\n\nBiography :-\n" + response.body()?.biography
                    }else{
                        tv.text = "No Information Available"
                    }
                }
            }
        })

        service.moviecast(pos).enqueue(object : Callback<Moviecastarray> {
            override fun onFailure(call: Call<Moviecastarray>, t: Throwable) {
                tv.text="Loading failed!"
            }
            override fun onResponse(
                call: Call<Moviecastarray>,
                response: Response<Moviecastarray>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@CastDetails,LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = Moviecastadapter(this@CastDetails, response.body()!!.cast)
                }
            }
        })

        seeMore.setOnClickListener {
            seeMore.visibility = View.GONE
            seeLess.visibility = View.VISIBLE
            tv.maxLines = Integer.MAX_VALUE
        }
        seeLess.setOnClickListener {
            seeLess.visibility = View.GONE
            seeMore.visibility = View.VISIBLE
            tv.maxLines = 15
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}