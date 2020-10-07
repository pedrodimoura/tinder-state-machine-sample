package com.github.pedrodimoura.statemachinesample.common

sealed class ExecutionState<out T> {
    data class Success<out T>(val data: T) : ExecutionState<T>()
    data class Failure(val reason: Throwable) : ExecutionState<Nothing>()
}