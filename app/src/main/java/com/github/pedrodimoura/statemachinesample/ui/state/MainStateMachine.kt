package com.github.pedrodimoura.statemachinesample.ui.state

import com.github.pedrodimoura.statemachinesample.domain.model.User

sealed class State {
    object Idle : State()
    data class Working(val action: () -> Unit) : State()
    data class ParametrizedWorking(val action: (Param) -> Unit, val param: Param) : State()
    object Waiting : State()
}

sealed class Event {
    object GetString : Event()
    data class ParametrizedGetUserProfile(val userLoginParam: UserLoginParam) : Event()
    data class Success(val user: User) : Event()
    data class Failure(val reason: Throwable) : Event()
    object Done : Event()
}

sealed class SideEffect {
    object Loading : SideEffect()
    data class Success(val user: User) : SideEffect()
    data class Failure(val reason: Throwable) : SideEffect()
    object Done : SideEffect()
}

interface Param

data class UserLoginParam(val login: String): Param
