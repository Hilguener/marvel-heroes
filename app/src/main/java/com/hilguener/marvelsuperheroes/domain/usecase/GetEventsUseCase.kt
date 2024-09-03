package com.hilguener.marvelsuperheroes.domain.usecase

import androidx.paging.PagingSource
import com.hilguener.marvelsuperheroes.data.paging.EventsPagingSource
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.events.Event

class GetEventsUseCase(private val httpRepository: HttpRepository) {
    fun getPagingSource(query: String? = null): PagingSource<Int, Event> {
        return EventsPagingSource(httpRepository, query)
    }
}
