package com.github.pedrodimoura.statemachinesample.data.remote.impl

import com.github.pedrodimoura.statemachinesample.data.remote.UserRemote
import com.github.pedrodimoura.statemachinesample.data.remote.model.UserRequest
import com.github.pedrodimoura.statemachinesample.data.remote.model.UserResponse

class UserRemoteImpl(
    private val gitHubRemoteApi: GitHubRemoteApi
) : UserRemote {
    override suspend fun getUser(userRequest: UserRequest): UserResponse =
        gitHubRemoteApi.getUser(userRequest.login)
}
