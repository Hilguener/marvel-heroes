package com.hilguener.marvelsuperheroes.domain.usecase

import androidx.paging.PagingSource
import com.hilguener.marvelsuperheroes.data.paging.SeriesPagingSource
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.series.Series

class GetSeriesUseCase(private val httpRepository: HttpRepository) {
    fun getPagingSource(
        query: String? = null,
    ): PagingSource<Int, Series> {
        return SeriesPagingSource(httpRepository, query)
    }
}
