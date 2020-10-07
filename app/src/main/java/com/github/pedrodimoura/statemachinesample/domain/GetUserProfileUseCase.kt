package com.github.pedrodimoura.statemachinesample.domain

import com.github.pedrodimoura.statemachinesample.common.ExecutionState
import com.github.pedrodimoura.statemachinesample.domain.model.GetUserProfileParam
import com.github.pedrodimoura.statemachinesample.domain.model.User
import com.github.pedrodimoura.statemachinesample.domain.repository.UserRepository

class GetUserProfileUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(getUserProfileParam: GetUserProfileParam): ExecutionState<User> =
        try {
            ExecutionState.Success(userRepository.getUser(getUserProfileParam.login))
        } catch (e: Throwable) {
            ExecutionState.Failure(e)
        }
}