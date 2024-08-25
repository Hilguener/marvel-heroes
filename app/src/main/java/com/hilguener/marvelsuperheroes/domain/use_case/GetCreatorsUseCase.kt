package com.hilguener.marvelsuperheroes.domain.use_case

import androidx.paging.PagingSource
import com.hilguener.marvelsuperheroes.data.paging.CreatorsPagingSource
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.creators.Creator

class GetCreatorsUseCase(private val httpRepository: HttpRepository) {
    fun getPagingSource(query: String? = null): PagingSource<Int, Creator> {
        return CreatorsPagingSource(httpRepository, query)
    }
}