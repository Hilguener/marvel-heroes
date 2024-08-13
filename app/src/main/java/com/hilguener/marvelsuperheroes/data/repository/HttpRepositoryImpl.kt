package com.hilguener.marvelsuperheroes.data.repository

import com.hilguener.marvelsuperheroes.data.http.MarvelApi
import com.hilguener.marvelsuperheroes.data.util.Constants
import com.hilguener.marvelsuperheroes.domain.model.character.CharactersDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.comic.ComicsDataWrapper
import com.hilguener.marvelsuperheroes.domain.model.series.SeriesDataWrapper
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
        return marvelApi.getComicsCharactersById(comicId)
    }

    override suspend fun getSeries(page: Int, name: String?): Response<SeriesDataWrapper> {
       return marvelApi.getSeries(offset = page, titleStartsWith = name)
    }

}
