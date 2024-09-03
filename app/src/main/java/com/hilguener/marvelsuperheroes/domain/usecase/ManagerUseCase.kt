package com.hilguener.marvelsuperheroes.domain.usecase

import androidx.paging.PagingSource
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic
import com.hilguener.marvelsuperheroes.domain.model.creators.Creator
import com.hilguener.marvelsuperheroes.domain.model.events.Event
import com.hilguener.marvelsuperheroes.domain.model.series.Series
import com.hilguener.marvelsuperheroes.domain.model.stories.Story

data class ManagerUseCase(
    val getCharactersUseCase: GetCharactersUseCase,
    val getCharactersComicsById: GetCharactersComicsByIdUseCase,
    val getComicsUseCase: GetComicsUseCase,
    val getSeriesUseCase: GetSeriesUseCase,
    val getEventsUseCase: GetEventsUseCase,
    val getStoriesUseCase: GetStoriesUseCase,
    val getCreatorsUseCase: GetCreatorsUseCase,
    val getCreatorComicsById: GetCreatorComicsById,
    val getCharactersComicById: GetCharactersComicByIdUseCase,
    val getCharactersCarrousel: GetCharactersCarrouselUseCase,
    val getComicsCarrousel: GetComicsCarrouselUseCase,
    val getSeriesCarrousel: GetSeriesCarrouselUseCase,
    val getEventsCarrousel: GetEventsCarrouselUseCase,
) {
    fun getCharactersPagingSource(query: String? = null): PagingSource<Int, Character> {
        return getCharactersUseCase.getPagingSource(query)
    }

    fun getComicsPagingSource(query: String? = null): PagingSource<Int, Comic> {
        return getComicsUseCase.getPagingSource(query)
    }

    fun getSeriesPagingSource(query: String? = null): PagingSource<Int, Series> {
        return getSeriesUseCase.getPagingSource(query)
    }

    fun getEventsPagingSource(query: String? = null): PagingSource<Int, Event> {
        return getEventsUseCase.getPagingSource(query)
    }

    fun getStoriesPagingSource(): PagingSource<Int, Story> {
        return getStoriesUseCase.getPagingSource()
    }

    fun getCreatorsPagingSource(query: String? = null): PagingSource<Int, Creator> {
        return getCreatorsUseCase.getPagingSource(query)
    }
}
