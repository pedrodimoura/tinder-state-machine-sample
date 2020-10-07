package com.github.pedrodimoura.statemachinesample

import android.app.Application
import com.github.pedrodimoura.statemachinesample.di.networkModule
import com.github.pedrodimoura.statemachinesample.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StateMachineApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            this.modules(networkModule, userModule)
        }
    }

}
