package com.github.pedrodimoura.statemachinesample.data.remote.impl

import com.github.pedrodimoura.statemachinesample.data.remote.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRemoteApi {

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): UserResponse
}
