package com.example.imdbclone.Others

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("now_playing?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun nowShowing(): Call<Tmdb2>

    @GET("popular?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun popularMovies(): Call<Tmdb2>

    @GET("upcoming?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun upcoming(): Call<Tmdb2>

    @GET("top_rated?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun toprated(): Call<Tmdb2>

    @GET("{Id}/credits?api_key=20ddfcf94f3bf96b48118c43a689756c")
    fun cast(@Path("Id")id:Int): Call<Cast>

    @GET("{Id}/similar?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun similarmovies(@Path("Id")id:Int): Call<Tmdb2>

    @GET("{Id}/videos?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun trailers(@Path("Id")id:Int): Call<Trailerarray>

    @GET("{Id}?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    fun overview(@Path("Id")id:Int):Call<Overview>

    @GET("person/{Id}?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    fun castinfo(@Path("Id")id:Int):Call<Castinfo>

    @GET("person/{Id}/movie_credits?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    fun moviecast(@Path("Id")id:Int):Call<Moviecastarray>

    @GET("search/movie?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&query&page=1&include_adult=false")
    fun search(@Query("query")q:String):Call<Searcharray>

    //@GET("")

}






/*
import retrofit2.Call
import retrofit2.http.GET

interface TmdbService {
    @GET("search/users?q=%22Pulkit%20Aggarwal%22")
    fun listUsers(): Call<>

    @GET("search/users?q=%22Pulkit%20Aggarwal%22")
    fun listRepos(): Call<>
}*/
