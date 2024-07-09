package com.hilguener.marvelsuperheroes.data.http

import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.character.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.LIMIT
    ): Response<MarvelResponse>

}