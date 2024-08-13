package com.hilguener.marvelsuperheroes.domain.use_case

import androidx.paging.PagingSource
import com.hilguener.marvelsuperheroes.data.paging.ComicsPagingSource
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.comic.Comic

class GetComicsUseCase(private val httpRepository: HttpRepository) {
    fun getPagingSource(
        query: String? = null
    ): PagingSource<Int, Comic> {
        return ComicsPagingSource(httpRepository, query)
    }
}