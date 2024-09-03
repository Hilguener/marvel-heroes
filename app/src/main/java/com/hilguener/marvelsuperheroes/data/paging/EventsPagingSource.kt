package com.hilguener.marvelsuperheroes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.events.Event

class EventsPagingSource(
    private val eventRepository: HttpRepository,
    private val name: String? = null,
) : PagingSource<Int, Event>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = eventRepository.getEvents(nextPageNumber, name)
            val events = response.body()?.data?.results ?: emptyList()
            val totalCount = response.body()?.data?.total ?: 0
            val nextKey = if (events.isNotEmpty() && events.size == Constants.LIMIT) nextPageNumber + 1 else null

            LoadResult.Page(
                data = events,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (nextPageNumber * Constants.LIMIT >= totalCount) null else nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Event>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
