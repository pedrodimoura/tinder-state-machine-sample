package com.github.pedrodimoura.statemachinesample.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int
)
