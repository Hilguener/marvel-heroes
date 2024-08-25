package com.hilguener.marvelsuperheroes.data.repository

import com.hilguener.marvelsuperheroes.data.http.MarvelApi
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.character.CharactersDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.comic.ComicsDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.creators.CreatorDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.events.EventDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.series.SeriesDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.stories.StoryDataWrapper
import retrofit2.Response

class HttpRepositoryImpl(private val marvelApi: MarvelApi) : HttpRepository {
    override suspend fun getCharacters(page: Int, name: String?): Response<CharactersDataWrapper> {
        val offset = (page - 1) * Constants.LIMIT
        return marvelApi.getCharacters(offset = offset, nameStartsWith = name)
    }

    override suspend fun getCharacterComicsById(characterId: Int): Response<ComicsDataWrapper> {
        return marvelApi.getCharacterComicsById(characterId)
    }

    override suspend fun getComics(page: Int, name: String?): Response<ComicsDataWrapper> {
        val offset = (page - 1) * Constants.LIMIT
        return marvelApi.getComics(offset = offset, titleStartsWith = name)
    }

    override suspend fun getComicsCharactersById(comicId: Int): Response<ComicsDataWrapper> {
        return marvelApi.getCharactersComicById(comicId)
    }

    override suspend fun getCharactersComicById(comicId: Int): Response<CharactersDataWrapper> {
        return marvelApi.getComicCharactersById(comicId)
    }


    override suspend fun getSeries(page: Int, name: String?): Response<SeriesDataWrapper> {
        val offset = (page - 1) * Constants.LIMIT
        return marvelApi.getSeries(offset = offset, titleStartsWith = name)
    }

    override suspend fun getEvents(page: Int, name: String?): Response<EventDataWrapper> {
        val offset = (page - 1) * Constants.LIMIT
        return marvelApi.getEvents(offset = offset, nameStartsWith = name)
    }

    override suspend fun getStories(page: Int): Response<StoryDataWrapper> {
        val offset = (page - 1) * Constants.LIMIT
        return marvelApi.getStories(offset = offset)
    }

    override suspend fun getCreators(page: Int, name: String?): Response<CreatorDataWrapper> {
        val offset = (page - 1) * Constants.LIMIT
        return marvelApi.getCreators(offset = offset, nameStartsWith = name)
    }

    override suspend fun getCreatorComicsById(creatorId: Int): Response<ComicsDataWrapper> {
        return marvelApi.getCreatorComicsById(creatorId)
    }

}
