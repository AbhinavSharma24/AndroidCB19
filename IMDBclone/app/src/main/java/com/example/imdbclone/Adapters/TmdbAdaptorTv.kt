package com.example.imdbclone.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Activities.Details
import com.example.imdbclone.Activities.DetailsTv
import com.example.imdbclone.Others.TmdbTvResponse
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_github.view.*
import java.util.ArrayList

class TmdbAdaptorTv(val context: Context, private val arrayList: ArrayList<TmdbTvResponse>)
    : RecyclerView.Adapter<TmdbAdaptorTv.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.item_github, parent, false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var currentuser: TmdbTvResponse? = null
        private var currentposition = 0

        init {
            itemView.setOnClickListener {
                val detail= Intent(context, DetailsTv::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun bind(user: TmdbTvResponse, position: Int) {
            this.currentuser = user
            this.currentposition = position
            with(itemView) {
                titletv.text = user.name
                ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
                Picasso.get().load("https://image.tmdb.org/t/p/original" + user.backdrop_path).into(img)
            }
        }

    }
}