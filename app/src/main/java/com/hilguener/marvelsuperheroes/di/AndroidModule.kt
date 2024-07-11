package com.hilguener.marvelsuperheroes.di

import com.google.gson.GsonBuilder
import com.hilguener.marvelsuperheroes.data.http.MarvelApi
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.repository.HttpRepositoryImpl
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.usecase.GetCharactersUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.ManagerUseCase
import com.hilguener.marvelsuperheroes.presentation.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val androidModule =
    module {
        single<MarvelApi> {
            val loggingInterceptor =
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

            val httpClient =
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

            val gson =
                GsonBuilder()
                    .setLenient()
                    .create()

            val retrofit =
                Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build()

            retrofit.create(MarvelApi::class.java)
        }

        single<HttpRepository> { HttpRepositoryImpl(get()) }

        single { GetCharactersUseCase(get()) }

        single { ManagerUseCase(get()) }

        viewModel { MainViewModel(get()) }
    }
