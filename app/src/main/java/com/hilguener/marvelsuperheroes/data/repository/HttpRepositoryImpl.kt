package com.hilguener.marvelsuperheroes.data.repository

import com.hilguener.marvelsuperheroes.data.http.MarvelApi
import com.hilguener.marvelsuperheroes.domain.model.character.MarvelResponse
import retrofit2.Response

class HttpRepositoryImpl(private val marvelApi: MarvelApi) : HttpRepository {
    override suspend fun getCharacters(): Response<MarvelResponse> {
        return marvelApi.getCharacters()
    }
}

