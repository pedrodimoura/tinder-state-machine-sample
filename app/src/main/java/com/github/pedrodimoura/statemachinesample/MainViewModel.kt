package com.github.pedrodimoura.statemachinesample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinder.StateMachine

class MainViewModel : ViewModel() {

    val liveData = MutableLiveData<String>()

    private val stateMachine: StateMachine<State, Event, SideEffect> by lazy {
        StateMachine.create {
            initialState(State.Idle)

            state<State.Idle> {
                on<Event.FetchData> { transitionTo(State.Loading, SideEffect.Loading) }
            }

            state<State.Loading> {
                onEnter { fetchData() }
                on<Event.FetchDataSucceeded> { event ->
                    transitionTo(
                        State.Success(event.payload),
                        SideEffect.Success
                    )
                }
                on<Event.FetchDataFailed> { event ->
                    transitionTo(
                        State.Failure(event.reason),
                        SideEffect.Failure
                    )
                }
            }

            state<State.Success<String>> {
                onEnter { liveData.value = this.data }
                on<Event.DataOnView> { transitionTo(State.Done, SideEffect.Done) }
                onExit { transitionTo(State.Done, SideEffect.Done) }
            }
            state<State.Done> {
                on<Event.Reload> { transitionTo(State.Loading, SideEffect.Loading) }
            }
        }
    }

    fun handle(event: Event) {
        stateMachine.transition(event)
    }

    private fun fetchData() {
        stateMachine.transition(Event.FetchDataSucceeded("Data"))
    }

}
