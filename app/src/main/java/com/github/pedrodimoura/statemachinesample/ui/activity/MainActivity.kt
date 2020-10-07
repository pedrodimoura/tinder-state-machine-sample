package com.github.pedrodimoura.statemachinesample.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.pedrodimoura.statemachinesample.databinding.ActivityMainBinding
import com.github.pedrodimoura.statemachinesample.ui.state.Event
import com.github.pedrodimoura.statemachinesample.ui.state.SideEffect
import com.github.pedrodimoura.statemachinesample.ui.state.UserLoginParam
import com.github.pedrodimoura.statemachinesample.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val SIDE_EFFECT_LOG_TAG = "SIDE_EFFECT"
    }

    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel.liveData.observe(this) { transition ->
            when (val s = transition.sideEffect) {
                is SideEffect.Loading -> Log.d(SIDE_EFFECT_LOG_TAG, "Loading")
                is SideEffect.Success -> {
                    Log.d(SIDE_EFFECT_LOG_TAG, "Success: ${s.user}")
                    viewModel.handle(Event.Done)
                }
                is SideEffect.Failure -> {
                    Toast.makeText(this, "Failure: ${s.reason}", Toast.LENGTH_SHORT).show()
                    viewModel.handle(Event.Done)
                }
                is SideEffect.Done -> Log.d(SIDE_EFFECT_LOG_TAG, "Done")
            }
        }

        viewBinding.parametrizedGet.setOnClickListener {
            val login = viewBinding.loginInput.text.toString()
            when {
                login.isNotEmpty() -> {
                    val userLoginParam = UserLoginParam(login)
                    viewModel.handle(Event.ParametrizedGetUserProfile(userLoginParam))
                }
                else -> Toast.makeText(this, "Informe um login", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
