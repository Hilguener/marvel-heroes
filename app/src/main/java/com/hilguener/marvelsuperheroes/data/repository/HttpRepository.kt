package com.hilguener.marvelsuperheroes.data.repository

import com.hilguener.marvelsuperheroes.domain.model.character.CharactersDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.comic.ComicsDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.creators.CreatorDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.events.EventDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.series.SeriesDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.stories.StoryDataWrapper
import retrofit2.Response

interface HttpRepository {
    suspend fun getCharacters(page: Int, name: String? = null): Response<CharactersDataWrapper>
    suspend fun getCharacterComicsById(characterId: Int): Response<ComicsDataWrapper>
    suspend fun getComics(page: Int, name: String? = null): Response<ComicsDataWrapper>
    suspend fun getComicsCharactersById(comicId: Int): Response<ComicsDataWrapper>
    suspend fun getCharactersComicById(comicId: Int): Response<CharactersDataWrapper>
    suspend fun getSeries(page: Int, name: String? = null): Response<SeriesDataWrapper>
    suspend fun getEvents(page: Int, name: String? = null): Response<EventDataWrapper>
    suspend fun getStories(page: Int): Response<StoryDataWrapper>
    suspend fun getCreators(page: Int, name: String? = null ): Response<CreatorDataWrapper>
    suspend fun getCreatorComicsById(creatorId: Int): Response<ComicsDataWrapper>
    suspend fun getCharactersCarrousel(): Response<CharactersDataWrapper>
    suspend fun getComicsCarrousel(): Response<ComicsDataWrapper>
    suspend fun getSeriesCarrousel(): Response<SeriesDataWrapper>
    suspend fun getEventsCarrousel(): Response<EventDataWrapper>
}
