package com.hilguener.marvelsuperheroes.data.http

import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.character.CharactersDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.comic.ComicsDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.creators.CreatorDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.events.EventDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.series.SeriesDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.stories.StoryDataWrapper
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
    suspend fun getComicCharactersById(
        @Path("comicId") comicId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 50,
        @Query("limit") limit: Int = Constants.LIMIT,
    ): Response<CharactersDataWrapper>

    @GET("/v1/public/comics/{comicId}/characters")
    suspend fun getCharactersComicById(
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

    @GET("/v1/public/events")
    suspend fun getEvents(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.LIMIT,
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Response<EventDataWrapper>

    @GET("/v1/public/stories")
    suspend fun getStories(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.LIMIT,
    ): Response<StoryDataWrapper>

    @GET("/v1/public/creators")
    suspend fun getCreators(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.LIMIT,
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Response<CreatorDataWrapper>

    @GET("/v1/public/creators/{creatorId}/comics")
    suspend fun getCreatorComicsById(
        @Path("creatorId") creatorId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.LIMIT,
    ): Response<ComicsDataWrapper>

    @GET("/v1/public/characters")
    suspend fun getCharactersCarrousel(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 301,
        @Query("limit") limit: Int = 20,
    ): Response<CharactersDataWrapper>

    @GET("/v1/public/comics")
    suspend fun getComicsCarrousel(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 100,
        @Query("limit") limit: Int = 20,
    ): Response<ComicsDataWrapper>


    @GET("/v1/public/series")
    suspend fun getSeriesCarrousel(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 104,
        @Query("limit") limit: Int = 6,
    ): Response<SeriesDataWrapper>

    @GET("/v1/public/events")
    suspend fun getEventsCarrousel(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20,
    ): Response<EventDataWrapper>
}
