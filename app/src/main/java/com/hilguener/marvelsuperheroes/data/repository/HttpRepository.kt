package com.hilguener.marvelsuperheroes.data.repository

import com.hilguener.marvelsuperheroes.domain.model.character.CharactersDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.comic.ComicsDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.series.SeriesDataWrapper
import retrofit2.Response

interface HttpRepository {
    suspend fun getCharacters(page: Int, name: String? = null): Response<CharactersDataWrapper>
    suspend fun getCharacterComicsById(characterId: Int): Response<ComicsDataWrapper>
    suspend fun getComics(page: Int, name: String? = null): Response<ComicsDataWrapper>
    suspend fun getComicsCharactersById(comicId: Int): Response<ComicsDataWrapper>
    suspend fun getSeries(page: Int, name: String? = null): Response<SeriesDataWrapper>
}
