package com.hilguener.marvelsuperheroes.domain.use_case

import androidx.paging.PagingSource
import com.hilguener.marvelsuperheroes.data.paging.StoriesPagingSource
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.domain.model.stories.Story

class GetStoriesUseCase(private val httpRepository: HttpRepository) {
    fun getPagingSource(): PagingSource<Int, Story> {
        return StoriesPagingSource(httpRepository)
    }
}