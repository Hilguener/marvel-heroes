package com.hilguener.marvelsuperheroes.domain.use_case

import androidx.paging.PagingSource
import com.hilguener.marvelsuperheroes.domain.model.character.Character
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic
import com.hilguener.marvelsuperheroes.domain.model.events.Event
import com.hilguener.marvelsuperheroes.domain.model.series.Series

data class ManagerUseCase(
    val getCharactersUseCase: GetCharactersUseCase,
    val getCharactersComicsById: GetCharactersComicsById,
    val getComicsUseCase: GetComicsUseCase,
    val getSeriesUseCase: GetSeriesUseCase,
    val getEventsUseCase: GetEventsUseCase
){
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
}
