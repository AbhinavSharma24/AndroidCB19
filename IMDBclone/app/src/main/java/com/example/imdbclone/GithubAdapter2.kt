package com.example.imdbclone

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.differentlayout.view.*

class GithubAdapter2( val context: Context, private val arrayList: ArrayList<GithubResponse>)
    : RecyclerView.Adapter<GithubAdapter2.GithubViewHolder>() {
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
        private var currentuser: GithubResponse? = null
        private var currentposition = 0
        init {
            itemView.setOnClickListener {
                val detail= Intent(context,Details::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(user: GithubResponse, position: Int) {
            this.currentuser = user
            this.currentposition = position
            with(itemView) {
                titletv.text = user.title
                ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).into(img1)
            }
        }
    }

}
