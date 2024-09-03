package com.hilguener.marvelsuperheroes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.series.Series

class SeriesPagingSource(
    private val seriesRepository: HttpRepository,
    private val name: String? = null,
) : PagingSource<Int, Series>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = seriesRepository.getSeries(nextPageNumber, name)
            val characters = response.body()?.data?.results ?: emptyList()
            val totalCount = response.body()?.data?.total ?: 0
            val nextKey =
                if (characters.isNotEmpty() && characters.size == Constants.LIMIT) nextPageNumber + 1 else null

            LoadResult.Page(
                data = characters,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (nextPageNumber * Constants.LIMIT >= totalCount) null else nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
