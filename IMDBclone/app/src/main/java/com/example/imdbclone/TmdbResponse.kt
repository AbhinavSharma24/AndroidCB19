package com.example.imdbclone


/*
import com.google.gson.annotations.SerializedName

data class GIthubResponse(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val items: List<>? = null
)*/


data class GithubResponse(

    val id: Int,
    val title:String,
    val vote_average:Float,
    val poster_path:String,
    val backdrop_path:String

)
data class Github2(val results:ArrayList<GithubResponse>)