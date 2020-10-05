package com.github.pedrodimoura.statemachinesample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.pedrodimoura.statemachinesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val SIDE_EFFECT_LOG_TAG = "SIDE_EFFECT"
    }

    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel.liveData.observe(this, Observer { Log.d(SIDE_EFFECT_LOG_TAG, it) })
        viewBinding.fetchDataButton.setOnClickListener { viewModel.handle(Event.FetchData) }
        viewBinding.reloadDataButton.setOnClickListener { viewModel.handle(Event.Reload) }
    }
}

sealed class State {
    object Idle : State()
    object Loading : State()
    data class Success<out T : Any>(val data: T) : State()
    data class Failure(val reason: Throwable) : State()
    object Done : State()
}

sealed class Event {
    object FetchData : Event()
    object Reload : Event()
    object DataOnView : Event()
    data class FetchDataSucceeded(val payload: String) : Event()
    data class FetchDataFailed(val reason: Throwable) : Event()
}

sealed class SideEffect {
    object Loading : SideEffect()
    object Success : SideEffect()
    object Failure : SideEffect()
    object Done : SideEffect()
}
