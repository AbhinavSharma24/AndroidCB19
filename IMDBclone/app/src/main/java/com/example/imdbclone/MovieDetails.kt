package com.example.imdbclone

data class MovieDetails (
    val character:String,
    val name:String,
    val profile_path:String,
    val id: Int
)
data class Cast(val cast:ArrayList<MovieDetails>)

data class Trailers(
    val key:String,
    val name:String

)
data class Trailerarray(val results:ArrayList<Trailers>)

data class Overview(
    val original_title:String,
    val overview:String,
    val release_date:String,
    val runtime:Int,
    val poster_path:String,
    val vote_average:Float,
    val backdrop_path:String,
    val genres:ArrayList<Genres>

)
data class Genres(
    val name: String
)

data class Castinfo(
    val name:String,
    val profile_path:String,
    val biography:String,
    val place_of_birth:String,
    val birthday:String
)
data class Moviecast(
    //val profile_path:String,
    val character:String,
    val poster_path:String,
    val title:String,
    val id:Int
)
data class Moviecastarray(val cast:ArrayList<Moviecast>)

data class Searchdetails(
    val title:String,
    val poster_path:String,
    val overview: String,
    val release_date:String,
    val id:Int
)
data class Searcharray(val results:ArrayList<Searchdetails>)