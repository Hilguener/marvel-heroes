package com.hilguener.marvelsuperheroes.data.repository

import com.hilguener.marvelsuperheroes.domain.model.character.MarvelResponse
import com.hilguener.marvelsuperheroes.domain.model.comic.ComicsResponse
import retrofit2.Response

interface HttpRepository {
    suspend fun getCharacters(): Response<MarvelResponse>
    suspend fun getComics(characterId: Int): Response<ComicsResponse>
}
