package com.github.pedrodimoura.statemachinesample.data.remote

import com.github.pedrodimoura.statemachinesample.data.remote.model.UserRequest
import com.github.pedrodimoura.statemachinesample.data.remote.model.UserResponse

interface UserRemote {

    suspend fun getUser(userRequest: UserRequest): UserResponse
}
