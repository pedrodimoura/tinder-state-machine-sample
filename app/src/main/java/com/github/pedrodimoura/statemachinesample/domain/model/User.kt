package com.github.pedrodimoura.statemachinesample.domain.model

data class User(
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String,
    val reposUrl: String,
    val name: String,
    val blog: String,
    val bio: String,
    val publicRepos: Int,
    val followers: Int,
    val following: Int
)
