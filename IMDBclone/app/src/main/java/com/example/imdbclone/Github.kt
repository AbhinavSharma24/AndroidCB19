package com.example.imdbclone

data class GithubUser(
    val login:String,
    val avatar_url:String
)
data class Github(val items:ArrayList<GithubUser>)