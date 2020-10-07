package com.github.pedrodimoura.statemachinesample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

        viewModel.liveData.observe(this) { transition ->
            when (val s = transition.sideEffect) {
                is SideEffect.Loading -> Log.d(SIDE_EFFECT_LOG_TAG, "Loading")
                is SideEffect.Success -> {
                    Log.d(SIDE_EFFECT_LOG_TAG, "Success: ${s.data}")
                    viewModel.handle(Event.Done)
                }
                is SideEffect.Failure -> {
                    Log.d(SIDE_EFFECT_LOG_TAG, "Failure: ${s.reason}")
                    viewModel.handle(Event.Done)
                }
                is SideEffect.Done -> Log.d(SIDE_EFFECT_LOG_TAG, "Done")
            }
        }

        viewBinding.noParametrizedGet.setOnClickListener { viewModel.handle(Event.GetString) }

        viewBinding.parametrizedGet.setOnClickListener {
            val filter = FilterParam("My Sentence")
            viewModel.handle(Event.ParametrizedGetString(filter))
        }
    }
}
