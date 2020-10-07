package com.github.pedrodimoura.statemachinesample.domain.repository

import com.github.pedrodimoura.statemachinesample.domain.model.User

interface UserRepository {
    suspend fun getUser(login: String): User
}
