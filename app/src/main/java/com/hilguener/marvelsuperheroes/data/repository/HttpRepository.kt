package com.hilguener.marvelsuperheroes.data.repository

import com.hilguener.marvelsuperheroes.domain.model.character.MarvelResponse
import retrofit2.Response

interface HttpRepository {
    suspend fun getCharacters(): Response<MarvelResponse>
}
