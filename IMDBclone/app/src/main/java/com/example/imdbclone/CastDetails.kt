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
import android.view.ViewTreeObserver
import android.animation.ValueAnimator
import android.animation.Animator
import kotlinx.android.synthetic.main.app_bar_main.*


@SuppressLint("Registered")
class CastDetails : AppCompatActivity() {
    //lateinit var mAnimator: ValueAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_castdetails)
//        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pos = intent.getIntExtra("castID",0)
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /*expandable.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                expandable.viewTreeObserver.removeOnPreDrawListener(this)
                expandable.visibility = View.GONE

                val widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                expandable.measure(widthSpec, heightSpec)

                mAnimator = slideAnimator(0, expandable.measuredHeight)
                return true
            }
        })

        seemore.setOnClickListener {
            if (expandable.visibility == View.GONE) {
                expand()
            } else {
                collapse()
            }
        }*/

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
                        tv.text = "\n\nAge: " + age + "\nBirthPlace: " + response.body()?.place_of_birth +
                                "\nBiography\n" + response.body()?.biography
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


    }

    /*private fun expand() {
        expandable.visibility = View.VISIBLE
        mAnimator.start()
    }

    private fun collapse() {
        val finalHeight = expandable.height
        val mAnimator = slideAnimator(finalHeight, 0)
        mAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animator: Animator) {
                //Height=0, but it set visibility to GONE
                expandable.visibility = View.GONE
            }
            override fun onAnimationStart(animator: Animator) {}
            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })
        mAnimator.start()
    }

    private fun slideAnimator(start: Int, end: Int): ValueAnimator {
        val animator = ValueAnimator.ofInt(start, end)
        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = expandable.layoutParams
            layoutParams.height = value
            expandable.layoutParams = layoutParams
        }
        return animator
    }*/


}