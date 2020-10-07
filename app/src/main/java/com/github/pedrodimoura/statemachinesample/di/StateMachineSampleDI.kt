package com.github.pedrodimoura.statemachinesample.di

import android.util.Log
import com.github.pedrodimoura.statemachinesample.data.remote.UserRemote
import com.github.pedrodimoura.statemachinesample.data.remote.impl.GitHubRemoteApi
import com.github.pedrodimoura.statemachinesample.data.remote.impl.UserRemoteImpl
import com.github.pedrodimoura.statemachinesample.data.repository.UserRepositoryImpl
import com.github.pedrodimoura.statemachinesample.domain.GetUserProfileUseCase
import com.github.pedrodimoura.statemachinesample.domain.repository.UserRepository
import com.github.pedrodimoura.statemachinesample.ui.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
}

val userModule = module {
    single<UserRemote> { UserRemoteImpl(gitHubRemoteApi = get<Retrofit>().create(GitHubRemoteApi::class.java)) }
    single<UserRepository> { UserRepositoryImpl(userRemote = get()) }
    factory { GetUserProfileUseCase(userRepository = get()) }
    viewModel { MainViewModel(getUserProfileUseCase = get()) }
}
