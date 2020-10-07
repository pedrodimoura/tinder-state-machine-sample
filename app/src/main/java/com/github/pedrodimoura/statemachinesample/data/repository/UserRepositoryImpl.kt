package com.github.pedrodimoura.statemachinesample.data.repository

import com.github.pedrodimoura.statemachinesample.data.extension.asUser
import com.github.pedrodimoura.statemachinesample.data.remote.UserRemote
import com.github.pedrodimoura.statemachinesample.data.remote.model.UserRequest
import com.github.pedrodimoura.statemachinesample.domain.model.User
import com.github.pedrodimoura.statemachinesample.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemote: UserRemote
) : UserRepository {
    override suspend fun getUser(login: String): User =
        userRemote.getUser(UserRequest(login)).asUser()
}
