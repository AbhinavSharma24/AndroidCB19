package com.example.imdbclone


data class TmdbResponse(

    val id: Int,
    val title:String,
    val vote_average:Float,
    val poster_path:String,
    val backdrop_path:String

)
data class Tmdb2(val results:ArrayList<TmdbResponse>)