package com.github.pedrodimoura.statemachinesample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinder.StateMachine

class MainViewModel : ViewModel() {

    val liveData = MutableLiveData<StateMachine.Transition.Valid<State, Event, SideEffect>>()

    private val stateMachine: StateMachine<State, Event, SideEffect> by lazy {
        StateMachine.create {
            initialState(State.Idle)

            state<State.Idle> {
                on<Event.GetString> {
                    transitionTo(State.Working(action = ::fetchData), SideEffect.Loading)
                }
                on<Event.ParametrizedGetString> {
                    transitionTo(
                        State.ParametrizedWorking(
                            action = ::fetchWithFilter,
                            param = it.filterParam
                        ), SideEffect.Loading
                    )
                }
            }

            state<State.Working> {
                onEnter { action.invoke() }
                on<Event.Success> { transitionTo(State.Waiting, SideEffect.Success(it.data)) }
                on<Event.Failure> { transitionTo(State.Waiting, SideEffect.Failure(it.reason)) }
            }

            state<State.ParametrizedWorking> {
                onEnter { action.invoke(param) }
                on<Event.Success> { transitionTo(State.Waiting, SideEffect.Success(it.data)) }
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

    private fun fetchData() = stateMachine.transition(Event.Success("{}"))

    private fun fetchWithFilter(param: Param) {
        val filter = param as? FilterParam ?: return
        stateMachine.transition(Event.Success("{${filter.sentence}}"))
    }

}
