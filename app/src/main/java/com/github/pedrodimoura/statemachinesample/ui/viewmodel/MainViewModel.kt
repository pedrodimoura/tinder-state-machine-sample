package com.github.pedrodimoura.statemachinesample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pedrodimoura.statemachinesample.common.ExecutionState
import com.github.pedrodimoura.statemachinesample.domain.GetUserProfileUseCase
import com.github.pedrodimoura.statemachinesample.domain.model.GetUserProfileParam
import com.github.pedrodimoura.statemachinesample.ui.state.*
import com.tinder.StateMachine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    val liveData = MutableLiveData<StateMachine.Transition.Valid<State, Event, SideEffect>>()

    private val stateMachine: StateMachine<State, Event, SideEffect> by lazy {
        StateMachine.create {
            initialState(State.Idle)

            state<State.Idle> {
                on<Event.ParametrizedGetUserProfile> {
                    transitionTo(
                        State.ParametrizedWorking(
                            action = ::getUserProfile,
                            param = it.userLoginParam
                        ), SideEffect.Loading
                    )
                }
            }

            state<State.Working> {
                onEnter { action.invoke() }
                on<Event.Success> { transitionTo(State.Waiting, SideEffect.Success(it.user)) }
                on<Event.Failure> { transitionTo(State.Waiting, SideEffect.Failure(it.reason)) }
            }

            state<State.ParametrizedWorking> {
                onEnter { action.invoke(param) }
                on<Event.Success> { transitionTo(State.Waiting, SideEffect.Success(it.user)) }
                on<Event.Failure> { transitionTo(State.Waiting, SideEffect.Failure(it.reason)) }
            }

            state<State.Waiting> {
                on<Event.Done> { transitionTo(State.Idle, SideEffect.Done) }
            }

            onTransition {
                val transition = it as? StateMachine.Transition.Valid ?: return@onTransition
                liveData.value = transition
            }
        }
    }

    fun handle(event: Event) = stateMachine.transition(event)

    private fun getUserProfile(param: Param) {
        val userLoginParam = param as? UserLoginParam ?: return
        viewModelScope.launch(Dispatchers.IO) {
            val result = getUserProfileUseCase.execute(GetUserProfileParam(userLoginParam.login))
            withContext(Dispatchers.Main) {
                stateMachine.transition(
                    when (result) {
                        is ExecutionState.Success -> Event.Success(result.data)
                        is ExecutionState.Failure -> Event.Failure(result.reason)
                    }
                )
            }
        }
    }
}
