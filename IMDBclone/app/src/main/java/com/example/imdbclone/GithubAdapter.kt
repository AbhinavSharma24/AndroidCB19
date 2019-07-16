package com.example.imdbclone

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_github.view.*

class GithubAdapter( val context: Context, private val arrayList: ArrayList<GithubResponse>)
    : RecyclerView.Adapter<GithubAdapter.GithubViewHolder>() {
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
        private var currentuser: GithubResponse? = null
        private var currentposition = 0

        init {
            itemView.setOnClickListener {
                val detail= Intent(context,Details::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)
            }
        }

        fun bind(user: GithubResponse, position: Int) {
            this.currentuser = user
            this.currentposition = position
            with(itemView) {
                titletv.text = user.title
                ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
                Picasso.get().load("https://image.tmdb.org/t/p/original" + user.backdrop_path).into(img)
            }
        }

    }
}