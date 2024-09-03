package com.hilguener.marvelsuperheroes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.stories.Story

class StoriesPagingSource(private val httpRepository: HttpRepository) : PagingSource<Int, Story>() {
    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = httpRepository.getStories(nextPageNumber)
            val stories = response.body()?.data?.results ?: emptyList()
            val totalCount = response.body()?.data?.total ?: 0
            val nextKey =
                if (stories.isNotEmpty() && stories.size == Constants.LIMIT) nextPageNumber + 1 else null

            LoadResult.Page(
                data = stories,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (nextPageNumber * Constants.LIMIT >= totalCount) null else nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
