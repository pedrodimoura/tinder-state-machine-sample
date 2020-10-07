package com.github.pedrodimoura.statemachinesample.data.extension

import com.github.pedrodimoura.statemachinesample.data.remote.model.UserResponse
import com.github.pedrodimoura.statemachinesample.domain.model.User

fun UserResponse.asUser() =
    User(login, avatarUrl, htmlUrl, reposUrl, name, blog, bio, publicRepos, followers, following)