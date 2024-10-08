package com.hilguener.marvelsuperheroes.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import com.hilguener.marvelsuperheroes.data.http.MarvelApi
import com.hilguener.marvelsuperheroes.data.repository.ConnectivityRepository
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.repository.HttpRepositoryImpl
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.usecase.GetCharactersCarrouselUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetCharactersComicByIdUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetCharactersComicsByIdUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetCharactersUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetComicsCarrouselUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetComicsUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetCreatorComicsById
import com.hilguener.marvelsuperheroes.domain.usecase.GetCreatorsUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetEventsCarrouselUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetEventsUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetSeriesCarrouselUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetSeriesUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.GetStoriesUseCase
import com.hilguener.marvelsuperheroes.domain.usecase.ManagerUseCase
import com.hilguener.marvelsuperheroes.presentation.home.vm.HomeViewModel
import com.hilguener.marvelsuperheroes.presentation.screen.characters.vm.CharactersViewModel
import com.hilguener.marvelsuperheroes.presentation.screen.comics.vm.ComicsViewModel
import com.hilguener.marvelsuperheroes.presentation.screen.creators.vm.CreatorsViewModel
import com.hilguener.marvelsuperheroes.presentation.screen.events.vm.EventsViewModel
import com.hilguener.marvelsuperheroes.presentation.screen.series.vm.SeriesViewModel
import com.hilguener.marvelsuperheroes.presentation.screen.stories.vm.StoriesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.N)
val androidModule =
    module {

        single<MarvelApi> {
            val loggingInterceptor =
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

            val httpClient =
                OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                    .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS).build()

            val gson = GsonBuilder().setLenient().create()

            val retrofit =
                Retrofit.Builder().baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient).build()

            retrofit.create(MarvelApi::class.java)
        }

        single<HttpRepository> { HttpRepositoryImpl(get()) }

        single<ConnectivityRepository> {
            ConnectivityRepository(get<Context>())
        }

        single { GetCharactersUseCase(get()) }
        single { GetCharactersComicsByIdUseCase(get()) }
        single { GetComicsUseCase(get()) }
        single { GetSeriesUseCase(get()) }
        single { GetEventsUseCase(get()) }
        single { GetStoriesUseCase(get()) }
        single { GetCreatorsUseCase(get()) }
        single { GetCreatorComicsById(get()) }
        single { GetCharactersComicByIdUseCase(get()) }
        single { GetCharactersCarrouselUseCase(get()) }
        single { GetComicsCarrouselUseCase(get()) }
        single { GetSeriesCarrouselUseCase(get()) }
        single { GetEventsCarrouselUseCase(get()) }
        single {
            ManagerUseCase(
                getCharactersUseCase = get(),
                getCharactersComicsById = get(),
                getComicsUseCase = get(),
                getSeriesUseCase = get(),
                getEventsUseCase = get(),
                getStoriesUseCase = get(),
                getCreatorsUseCase = get(),
                getCreatorComicsById = get(),
                getCharactersComicById = get(),
                getCharactersCarrousel = get(),
                getComicsCarrousel = get(),
                getSeriesCarrousel = get(),
                getEventsCarrousel = get(),
            )
        }

        viewModel { CharactersViewModel(get()) }
        viewModel { ComicsViewModel(get()) }
        viewModel { SeriesViewModel(get()) }
        viewModel { EventsViewModel(get()) }
        viewModel { StoriesViewModel(get()) }
        viewModel { CreatorsViewModel(get()) }
        viewModel { HomeViewModel(get()) }
    }
