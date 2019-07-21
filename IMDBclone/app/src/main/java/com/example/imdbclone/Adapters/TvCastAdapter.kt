package com.example.imdbclone.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.Activities.DetailsTv
import com.example.imdbclone.Others.Tvcast
import com.example.imdbclone.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.differentlayout.view.*

class TvCastAdapter(val context: Context, private val arrayList: ArrayList<Tvcast>)
    : RecyclerView.Adapter<TvCastAdapter.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.differentlayout, parent, false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: Tvcast? = null
        init {
            itemView.setOnClickListener {
                val detail= Intent(context, DetailsTv::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)
            }
        }

        fun bind(user: Tvcast, position: Int) {

            this.currentuser = user

            with(itemView) {
                titletv.text = user.name
                ratingtv.text = "As " + user.character
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).into(img2)
            }

        }
    }
}