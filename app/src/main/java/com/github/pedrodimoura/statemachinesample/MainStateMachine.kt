package com.github.pedrodimoura.statemachinesample

sealed class State {
    object Idle : State()
    data class Working(val action: () -> Unit) : State()
    data class ParametrizedWorking(val action: (Param) -> Unit, val param: Param) : State()
    object Waiting : State()
}

sealed class Event {
    object GetString : Event()
    data class ParametrizedGetString(val filterParam: FilterParam) : Event()
    data class Success(val data: String) : Event()
    data class Failure(val reason: Throwable) : Event()
    object Done : Event()
}

sealed class SideEffect {
    object Loading : SideEffect()
    data class Success(val data: String) : SideEffect()
    data class Failure(val reason: Throwable) : SideEffect()
    object Done : SideEffect()
}

interface Param

data class FilterParam(val sentence: String): Param
