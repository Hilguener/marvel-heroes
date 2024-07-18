package com.hilguener.marvelsuperheroes.di

import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import com.hilguener.marvelsuperheroes.data.http.MarvelApi
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.repository.HttpRepositoryImpl
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.use_case.authentication.AuthRepository
import com.hilguener.marvelsuperheroes.domain.use_case.authentication.AuthRepositoryImpl
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateConfirmPassword
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateEmail
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateName
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidatePassword
import com.hilguener.marvelsuperheroes.domain.usecase.GetCharactersUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.ManagerUseCase
import com.hilguener.marvelsuperheroes.presentation.signin.vm.SignInViewModel
import com.hilguener.marvelsuperheroes.presentation.signup.vm.SignUpViewModel
import com.hilguener.marvelsuperheroes.presentation.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val androidModule =
    module {
        single { FirebaseAuth.getInstance() }
        single { ValidateName() }
        single { ValidateEmail() }
        single { ValidatePassword() }
        single { ValidateConfirmPassword() }

        single<AuthRepository> { AuthRepositoryImpl(firebaseAuth = get()) }

        viewModel {
            SignUpViewModel(
                validateName = get(),
                validateEmail = get(),
                validatePassword = get(),
                validateConfirmPassword = get(),
                authRepository = get()
            )
        }

        viewModel {
            SignInViewModel(
                authRepository = get(),
                validateEmail = get(),
                validatePassword = get()
            )
        }

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
