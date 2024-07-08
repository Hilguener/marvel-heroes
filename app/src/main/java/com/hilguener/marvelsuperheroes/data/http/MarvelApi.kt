package com.hilguener.marvelsuperheroes.data.http

import com.hilguener.marvelsuperheroes.domain.model.character.MarvelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): Call<MarvelResponse>
}