package com.hilguener.marvelsuperheroes.data.http

import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.character.CharactersDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.comic.ComicsDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.series.SeriesDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.LIMIT,
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Response<CharactersDataWrapper>

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComicsById(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.LIMIT,
    ): Response<ComicsDataWrapper>

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 50,
        @Query("limit") limit: Int = Constants.LIMIT,
        @Query("titleStartsWith") titleStartsWith: String? = null
    ): Response<ComicsDataWrapper>

    @GET("/v1/public/comics/{comicId}/characters")
    suspend fun getComicsCharactersById(
        @Path("comicId") comicId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.LIMIT,
    ): Response<ComicsDataWrapper>

    @GET("/v1/public/series")
    suspend fun getSeries(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 50,
        @Query("limit") limit: Int = Constants.LIMIT,
        @Query("titleStartsWith") titleStartsWith: String? = null
    ): Response<SeriesDataWrapper>
}
