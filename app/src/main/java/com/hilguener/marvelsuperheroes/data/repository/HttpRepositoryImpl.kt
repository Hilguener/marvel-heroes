package com.hilguener.marvelsuperheroes.data.repository

import com.hilguener.marvelsuperheroes.data.http.MarvelApi
import com.hilguener.marvelsuperheroes.domain.model.character.MarvelResponse
import com.hilguener.marvelsuperheroes.domain.model.comic.ComicsResponse
import retrofit2.Response

class HttpRepositoryImpl(private val marvelApi: MarvelApi) : HttpRepository {
    override suspend fun getCharacters(): Response<MarvelResponse> {
        return marvelApi.getCharacters()
    }
    override suspend fun getComics(characterId: Int): Response<ComicsResponse> {
        return marvelApi.getComics(characterId)
    }
}
