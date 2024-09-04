package com.hilguener.marvelsuperheroes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hilguener.marvelsuperheroes.data.repository.HttpRepository
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.character.Character

class CharacterPagingSource(
    private val characterRepository: HttpRepository,
    private val name: String? = null,
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = characterRepository.getCharacters(nextPageNumber, name)
            val characters = response.body()?.data?.results ?: emptyList()
            val totalCount = response.body()?.data?.total ?: 0

            val nextKey = if ((nextPageNumber * Constants.LIMIT) < totalCount) nextPageNumber + 1 else null

            LoadResult.Page(
                data = characters,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
