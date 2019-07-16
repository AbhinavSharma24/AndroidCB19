package com.example.imdbclone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
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

        et.showSoftInputOnFocus = false

        //Retrofit
        service.nowShowing().enqueue(object : Callback<Github2> {
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed, check your internet connection !!!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
                    rview.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = GithubAdapter(this@MainActivity, response.body()!!.results)
                    /*rview.setOnClickListener {
                        startActivity(Intent(this@MainActivity,MovieAdapter2::class.java))
                    }*/

                    /*rview.addOnItemTouchListener(RecyclerItemClickListenr(this@MainActivity, rview, object : RecyclerItemClickListenr.OnItemClickListener {
                        override fun onItemLongClick(view: View?, position: Int) {

                        }
                        override fun onItemClick(view: View, position: Int) {
                            startActivity(Intent(this@MainActivity,MovieAdapter::class.java))
                        }
                    }))*/
                }
            }
        })
        service.popularMovies().enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview2.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter = GithubAdapter2(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.upcoming().enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview3.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter = GithubAdapter(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.toprated().enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview4.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rview4.adapter = GithubAdapter2(this@MainActivity, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        searchbutton.setOnClickListener {
            val a = Intent(this,Search::class.java)
            a.putExtra("Search Text",et.text.toString())
            startActivity(a)

        }

    }
}
