package com.hilguener.marvelsuperheroes.di

import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import com.hilguener.marvelsuperheroes.data.http.MarvelApi
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.repository.HttpRepositoryImpl
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.use_case.GetCharactersComicsById
import com.hilguener.marvelsuperheroes.domain.use_case.GetCharactersUseCase
import com.hilguener.marvelsuperheroes.domain.use_case.ManagerUseCase
import com.hilguener.marvelsuperheroes.domain.use_case.authentication.AuthRepository
import com.hilguener.marvelsuperheroes.domain.use_case.authentication.AuthRepositoryImpl
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateConfirmPassword
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateEmail
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidateName
import com.hilguener.marvelsuperheroes.domain.use_case.validation.ValidatePassword
import com.hilguener.marvelsuperheroes.presentation.characters.vm.CharactersViewModel
import com.hilguener.marvelsuperheroes.presentation.signin.vm.SignInViewModel
import com.hilguener.marvelsuperheroes.presentation.signup.vm.SignUpViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
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
        single { GetCharactersComicsById(get()) }

        single {
            ManagerUseCase(
                getCharactersUseCase = get(),
                getCharactersComicsById = get()
            )
        }

        viewModel { CharactersViewModel(get()) }

    }
